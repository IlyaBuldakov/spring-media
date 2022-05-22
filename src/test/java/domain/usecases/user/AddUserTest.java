package domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.entities.failures.AlreadyExists;
import domain.entities.user.User;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddUserTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final AddUser useCase = new AddUser(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldCreateUserByTheRepository() {
    // Arrange
    var user = User.createTestUser();
    // Act
    useCase.execute(user);
    // Assert
    Mockito.verify(mockUserRepository).add(user);
  }

  @Test
  void userDoesNotExist_ShouldCreateUserAndReturnUser()
          throws ExecutionException, InterruptedException {
    var user = User.createTestUser();
    Mockito
            .when(mockUserRepository.add(user))
            .thenReturn(CompletableFuture.completedFuture(Either.right(user)));
    var result = useCase.execute(user)
            .get()
            .get();
    assertThat(result).isEqualTo(user);
  }

  @Test
  void usersExist_ShouldReturnAlreadyExists() throws ExecutionException, InterruptedException {
    var user = User.createTestUser();
    var failure = new AlreadyExists();
    Mockito
            .when(mockUserRepository.add(user))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));
    var result = useCase.execute(user)
            .get()
            .getLeft();
    assertThat(result).isEqualTo(failure);
  }
}