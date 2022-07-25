package com.htc.domain.usecases.task;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.Task;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий обновления задачи.
 */
@Component
@AllArgsConstructor
public final class UpdateTask implements UseCase<UpdateTask.Params, Task> {

  /**
   * Параметры выполнения сценарий обновления задачи.
   *
   * @param id          Идентификатор задачи.
   * @param name        Название задачи.
   * @param contentType Тип медиаконтекта.
   * @param description Описание задачи.
   * @param authorId    Автор задачи.
   * @param executorId  Исполнитель задачи.
   * @param dateExpired Срок задачи.
   */
  public record Params(
          int id,
          String name,
          Content.Type contentType,
          String description,
          int authorId,
          int executorId,
          LocalDateTime dateExpired) {
  }

  private final TaskRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Params params) {
    InvalidValues invalidValues = new InvalidValues();

    var id = Id.create(params.id);
    if (id.isLeft()) {
      invalidValues.addInvalidValue(id.getLeft());
    }

    var name = Task.Name.create(params.name);
    if (name.isLeft()) {
      invalidValues.addInvalidValue(name.getLeft());
    }
    var description = Task.Description.create(params.description);
    var authorId = Id.create(params.authorId);
    var executorId = Id.create(params.executorId);

    if (!invalidValues.getInvalidValues().isEmpty()) {
      return CompletableFuture.completedFuture(Either.left(invalidValues));
    }

    return repository.update(
            id.get(),
            name.get(),
            params.contentType,
            description.get(),
            authorId.get(),
            executorId.get(),
            params.dateExpired);
  }
}
