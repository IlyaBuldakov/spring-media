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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateTaskTest {
  final TaskRepository mockTaskRepository = mock(TaskRepository.class);
  final UpdateTask useCase = new UpdateTask(mockTaskRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldUpdateTaskByTheRepository() {
    // Arrange
    var task = Task.createTestTask();

    // Act
    useCase.execute(task);

    // Assert
    verify(mockTaskRepository).update(task);
  }

  @Test
  void tasksExist_ShouldUpdateUserAndReturnTask() throws ExecutionException, InterruptedException {
    // Arrange
    var task = Task.createTestTask();

    when(mockTaskRepository.update(task))
            .thenReturn(CompletableFuture.completedFuture(Either.right(task)));

    // Act
    var result = useCase.execute(task)
            .get()
            .get();

    // Assert
    assertThat(result).isEqualTo(task);
  }

  @Test
  void tasksNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    // Arrange
    var task = Task.createTestTask();
    var failure = new NotFound();

    when(mockTaskRepository.update(task))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(task)
            .get()
            .getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}
