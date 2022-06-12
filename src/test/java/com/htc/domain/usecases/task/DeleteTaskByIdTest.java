package com.htc.domain.usecases.task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteTaskByIdTest {
  final TaskRepository mockTaskRepository = mock(TaskRepository.class);
  final DeleteTaskById useCase = new DeleteTaskById(mockTaskRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldDeleteTaskByTheRepository() {
    // Arrange
    var taskId = new Random().nextInt();

    // Act
    useCase.execute(taskId);

    // Assert
    verify(mockTaskRepository).delete(taskId);
  }

  @Test
  void taskExists_ShouldDeleteTaskAndReturnVoid() throws ExecutionException, InterruptedException {
    // Arrange
    var taskId = new Random().nextInt();

    when(mockTaskRepository.delete(taskId))
            .thenReturn(CompletableFuture.completedFuture(Either.right(null)));

    // Act
    var result = useCase.execute(taskId)
            .get()
            .get();

    // Assert
    assertThat(result).isNull();
  }

  @Test
  void taskDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    // Arrange
    var taskId = new Random().nextInt();
    var failure = new NotFound("");

    when(mockTaskRepository.delete(taskId))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(taskId)
            .get()
            .getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}
