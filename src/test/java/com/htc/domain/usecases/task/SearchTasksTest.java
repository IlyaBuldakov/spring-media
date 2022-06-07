package com.htc.domain.usecases.task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.htc.domain.entities.tasks.Task;
import com.htc.domain.entities.user.User;
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
class SearchTasksTest {
  final TaskRepository mockTaskRepository = mock(TaskRepository.class);
  final SearchTasks useCase = new SearchTasks(mockTaskRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void queryDoesNotContainRole_ShouldCallSearchMethodWithoutRoleArgumentOnly() {
    // Arrange
    var query = new SearchTasks.Query("test", null);

    // Act
    useCase.execute(query);

    // Assert
    verify(mockTaskRepository, times(1)).search(query.query());
    verify(mockTaskRepository, times(0)).search(query.query(), query.user());
  }

  @Test
  void queryContainsRole_ShouldCallSearchMethodWithRoleArgumentOnly() {
    // Arrange
    var query = new SearchTasks.Query(
            "test",
            User.createTestUser());

    // Act
    useCase.execute(query);

    // Assert
    verify(mockTaskRepository, times(1)).search(query.query(), query.user());
    verify(mockTaskRepository, times(0)).search(query.query());
  }

  @Test
  void queryDoesNotContainRole_ShouldGetUsersFromTheRepositoryAccordingToQuery()
          throws ExecutionException, InterruptedException {
    // Arrange
    var query = new SearchTasks.Query("test", null);
    var expected = List.of(Task.createTestTask());

    when(mockTaskRepository.search(query.query()))
            .thenReturn(CompletableFuture.completedFuture(Either.right(expected)));

    // Act
    var result = useCase.execute(query)
            .get()
            .get();

    // Assert
    assertThat(result).isEqualTo(expected);
  }

  @Test
  void queryContainsUser_ShouldGetTasksFromTheRepositoryAccordingToQuery()
          throws ExecutionException, InterruptedException {
    // Arrange
    var query = new SearchTasks.Query(
            "test",
            User.createTestUser());
    var expected = List.of(Task.createTestTask());

    when(mockTaskRepository.search(query.query(), query.user()))
            .thenReturn(CompletableFuture.completedFuture(Either.right(expected)));

    // Act
    var result = useCase.execute(query)
            .get()
            .get();

    // Assert
    assertThat(result).isEqualTo(expected);
  }
}