package domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import domain.entities.failures.AlreadyExists;
import domain.entities.user.User;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserTest {
  final UserRepository mockUserRepository = mock(UserRepository.class);
  final CreateUser useCase = new CreateUser(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldCreateUserByTheRepository() {
    // Arrange
    var user = User.createTestUser();

    // Act
    useCase.execute(user);

    // Assert
    verify(mockUserRepository).create(user);
  }

  @Test
  void usersNotExist_ShouldCreateUserAndReturnUser()
          throws ExecutionException, InterruptedException {
    // Arrange
    var user = User.createTestUser();

    when(mockUserRepository.create(user))
            .thenReturn(CompletableFuture.completedFuture(Either.right(user)));

    // Act
    var result = useCase.execute(user)
            .get()
            .get();

    // Assert
    assertThat(result).isEqualTo(user);
  }

  @Test
  void usersExist_ShouldReturnAlreadyExists()
          throws ExecutionException, InterruptedException {
    // Arrange
    var user = User.createTestUser();
    var failure = new AlreadyExists();

    when(mockUserRepository.create(user))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(user)
            .get()
            .getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}