package com.htc.domain.usecases.task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.htc.domain.entities.tasks.Task;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllTasksTest {
  final TaskRepository mockTaskRepository = mock(TaskRepository.class);
  final GetAllTasks useCase = new GetAllTasks(mockTaskRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldGetAllTasksFromTheRepository() {
    // Act
    useCase.execute(null);

    // Assert
    verify(mockTaskRepository).getAll();
  }

  @Test
  void tasksExist_ShouldReturnAllTasks() throws ExecutionException, InterruptedException {
    // Arrange
    var tasks = List.of(Task.createTestTask());

    when(mockTaskRepository.getAll())
            .thenReturn(CompletableFuture.completedFuture(Either.right(tasks)));

    // Act
    var result = useCase.execute(null)
            .get()
            .get();

    // Assert
    assertThat(result).isEqualTo(tasks);
  }

  @Test
  void tasksNotExist_ShouldReturnEmptyList() throws ExecutionException, InterruptedException {
    // Arrange
    var tasks = List.<Task>of();

    when(mockTaskRepository.getAll())
            .thenReturn(CompletableFuture.completedFuture(Either.right(tasks)));

    // Act
    var result = useCase.execute(null)
            .get()
            .get();

    // Assert
    assertThat(result).isEqualTo(tasks);
  }
}