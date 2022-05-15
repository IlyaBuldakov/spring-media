package domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.entities.failures.NotFound;
import domain.entities.user.User;
import domain.entities.user.UserRole;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserByIdTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final GetUserById useCase = new GetUserById(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldGetUserFromTheRepository() {
    var testUserId = new Random().nextInt();
    useCase.execute(testUserId);
    Mockito.verify(mockUserRepository).get(testUserId);
  }

  @Test
  void userExists_ShouldReturnUser() throws ExecutionException, InterruptedException {
    //arrange
    var testUserId = new Random().nextInt();
    final User user = new User(
            testUserId,
            "mail@mail.ru",
            "hardPassword",
            "First Second Name",
            "1234567890==",
            new UserRole(1, UserRole.RoleType.ADMIN)
    );
    Mockito
            .when(mockUserRepository.get(testUserId))
            .thenReturn(CompletableFuture.completedFuture(Either.right(user)));
    //act
    var result = useCase.execute(testUserId)
            .get()
            .get();
    //assert
    assertThat(result).isEqualTo(user);
  }

  @Test
  void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    var badTestUserId = new Random().nextInt();
    var failure = new NotFound();
    Mockito
            .when(mockUserRepository.get(badTestUserId))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));
    var result = useCase.execute(badTestUserId)
            .get()
            .getLeft();
    assertThat(result).isEqualTo(failure);
  }
}
