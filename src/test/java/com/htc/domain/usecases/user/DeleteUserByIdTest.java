package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteUserByIdTest {
  final UserRepository mockUserRepository = mock(UserRepository.class);
  final DeleteUserById useCase = new DeleteUserById(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldDeleteUserByTheRepository() {
    // Arrange
    var userId = new Random().nextInt();

    // Act
    useCase.execute(userId);

    // Assert
    verify(mockUserRepository).delete(userId);
  }

  @Test
  void userExists_ShouldDeleteUserAndReturnVoid() throws ExecutionException, InterruptedException {
    // Arrange
    var userId = new Random().nextInt();

    when(mockUserRepository.delete(userId))
            .thenReturn(CompletableFuture.completedFuture(Either.right(null)));

    // Act
    var result = useCase.execute(userId)
            .get()
            .get();

    // Assert
    assertThat(result).isNull();
  }

  @Test
  void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    // Arrange
    var userId = new Random().nextInt();
    var failure = NotFound.DEFAULT_MESSAGE;

    when(mockUserRepository.delete(userId))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(userId)
            .get()
            .getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}
