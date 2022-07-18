package com.htc.domain.usecases.task;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий создания задачи.
 */
@Component
@AllArgsConstructor
public class CreateTask implements UseCase<CreateTask.Params, Task> {
  public record Params(
      String name, String nameKey,
      Content.Type type, String typeKey,
      String description, String descriptionKey,
      int[] fileId, String fileIdKey,
      int authorId, String authorIdKey,
      int executorId, String executorIdKey,
      LocalDateTime dateCreated, String dateCreatedKey,
      LocalDateTime dateExpired, String dateExpiredKey,
      int[] contentsId, String contentsIdKey,
      int[] commentsId, String commentsIdKey,
      Task.Status status, String statusKey) {}

  private final TaskRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Params params) {
    return repository.create(
        params.name(),
        params.type(),
        params.description(),
        params.fileId(),
        params.authorId(),
        params.executorId,
        params.dateCreated,
        params.dateExpired,
        params.contentsId,
        params.commentsId,
        params.status);
  }
}
