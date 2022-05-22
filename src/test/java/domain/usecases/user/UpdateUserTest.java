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
class UpdateUserTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final UpdateUser useCase = new UpdateUser(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldUpdateUserByTheRepository() {
    // Arrange
    var user = new User(
            new Random().nextInt(),
            "mail@mail.ru",
            "hardPassword",
            "First Second Name",
            "1234567890==",
            new UserRole(1, UserRole.RoleType.ADMIN)
    );
    // Act
    useCase.execute(user);
    // Assert
    Mockito.verify(mockUserRepository).update(user);
  }

  @Test
  void usersExist_ShouldUpdateUser() throws ExecutionException, InterruptedException {
    var user = new User(
            new Random().nextInt(),
            "mail@mail.ru",
            "hardPassword",
            "First Second Name",
            "1234567890==",
            new UserRole(1, UserRole.RoleType.ADMIN)
    );
    Mockito
            .when(mockUserRepository.update(user))
            .thenReturn(CompletableFuture.completedFuture(Either.right(user)));
    var result = useCase.execute(user)
            .get()
            .get();
    assertThat(result).isEqualTo(user);
  }

  @Test
  void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    var user = new User(
            new Random().nextInt(),
            "mail@mail.ru",
            "hardPassword",
            "First Second Name",
            "1234567890==",
            new UserRole(1, UserRole.RoleType.ADMIN)
    );
    var failure = new NotFound();
    Mockito
            .when(mockUserRepository.update(user))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));
    var result = useCase.execute(user)
            .get()
            .getLeft();
    assertThat(result).isEqualTo(failure);
  }
}
