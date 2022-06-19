package com.htc.domain.usecases.task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.htc.domain.entities.failures.AlreadyExists;
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
class CreateTaskTest {

  final TaskRepository mockTaskRepository = mock(TaskRepository.class);

  final CreateTask useCase = new CreateTask(mockTaskRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldCreateTaskByTheRepository() {
    //Arrange
    var task = Task.createTestTask();

    // Act
    useCase.execute(task);

    // Assert
    verify(mockTaskRepository).create(task);
  }

  @Test
  void taskNotExist_ShouldCreateTaskAndReturnTask()
          throws ExecutionException, InterruptedException {
    // Arrange
    var task = Task.createTestTask();
    when(mockTaskRepository.create(task))
            .thenReturn(CompletableFuture.completedFuture(Either.right(task)));

    // Act
    var result = useCase.execute(task)
            .get()
            .get();

    // Assert
    assertThat(result).isEqualTo(task);
  }

  @Test
  void taskExist_ShouldReturnAlreadyExists()
          throws ExecutionException, InterruptedException {
    // Arrange
    var task = Task.createTestTask();
    var failure = AlreadyExists.DEFAULT_MESSAGE;

    when(mockTaskRepository.create(task))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(task)
            .get()
            .getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}