package com.htc.domain.usecases.task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.tasks.Task;
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
class GetTaskByIdTest {
  final TaskRepository mockTaskRepository = mock(TaskRepository.class);
  final GetTaskById useCase = new GetTaskById(mockTaskRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldGetTaskFromTheRepository() {
    // Arrange
    var taskId = new Random().nextInt();

    // Act
    useCase.execute(taskId);

    // Assert
    verify(mockTaskRepository).get(taskId);
  }

  @Test
  void taskExists_ShouldReturnTask() throws ExecutionException, InterruptedException {
    // Arrange
    var taskId = new Random().nextInt();
    var task = Task.createTestTask(taskId);

    when(mockTaskRepository.get(taskId))
            .thenReturn(CompletableFuture.completedFuture(Either.right(task)));

    // Act
    var result = useCase.execute(taskId)
            .get()
            .get();

    // Assert
    assertThat(result).isEqualTo(task);
  }

  @Test
  void taskDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    // Arrange
    var taskId = new Random().nextInt();
    var failure = NotFound.DEFAULT_MESSAGE;

    when(mockTaskRepository.get(taskId))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(taskId)
            .get()
            .getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}
