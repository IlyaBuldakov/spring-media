package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
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
    var testUserId = new Random().nextInt(32);
    useCase.execute(testUserId);
    Mockito.verify(mockUserRepository).delete(testUserId);
  }

  @Test
  void userExists_ShouldDeleteUserAndReturnVoid() throws ExecutionException, InterruptedException {
    // Arrange
    var testUserId = new Random().nextInt(32);
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
    var badTestUserId = new Random().nextInt(32);
    var failure = new NotFound("");
    Mockito
            .when(mockUserRepository.delete(badTestUserId))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));
    var result = useCase.execute(badTestUserId)
            .get()
            .getLeft();
    assertThat(result).isEqualTo(failure);
  }
}
