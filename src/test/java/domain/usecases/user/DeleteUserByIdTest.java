package domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.entities.failures.NotFound;
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
class DeleteUserByIdTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final DeleteUserById useCase = new DeleteUserById(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldDeleteUserByTheRepository() {
    var testUserId = new Random().nextInt();
    useCase.execute(testUserId);
    Mockito.verify(mockUserRepository).delete(testUserId);
  }

  @Test
  void userExists_ShouldDeleteUserAndReturnVoid() throws ExecutionException, InterruptedException {
    // Arrange
    var testUserId = new Random().nextInt();
    Mockito
            .when(mockUserRepository.delete(testUserId))
            .thenReturn(CompletableFuture.completedFuture(Either.right(null)));
    // Act
    var result = useCase.execute(testUserId)
            .get()
            .get();
    // Assert
    assertThat(result).isNull();
  }

  @Test
  void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    var badTestUserId = new Random().nextInt();
    var failure = new NotFound();
    Mockito
            .when(mockUserRepository.delete(badTestUserId))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));
    var result = useCase.execute(badTestUserId)
            .get()
            .getLeft();
    assertThat(result).isEqualTo(failure);
  }
}
