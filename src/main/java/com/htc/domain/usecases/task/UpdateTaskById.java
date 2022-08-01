package com.htc.domain.usecases.task;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.Results;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий обновления данных задачи.
 */
@Component
@AllArgsConstructor
public final class UpdateTaskById implements UseCase<UpdateTaskById.Params, Task> {
  /**
   * Параметры сценария создания задачи.
   *
   * @param name Название задачи.
   * @param nameKey Ключ названия задачи.
   * @param type Тип контента.
   * @param typeKey Ключ типа контента.
   * @param description Описание задачи.
   * @param descriptionKey Ключ описания задачи.
   * @param authorId Идентификатор автора задачи.
   * @param authorIdKey Ключ идентификатора автора задачи.
   * @param executorId Идентификатор исполнителя задачи.
   * @param executorIdKey Ключ идентификатора исполнителя задачи.
   * @param dateExpired Дата окончания задачи.
   * @param dateExpiredKey Ключ даты окончания задачи.
   * @param status Статус задачи.
   * @param statusKey Ключ статуса задачи.
   */
  public record Params(
      int id, String idKey,
      String name, String nameKey,
      Content.Type type, String typeKey,
      String description, String descriptionKey,
      int authorId, String authorIdKey,
      int executorId, String executorIdKey,
      LocalDateTime dateExpired, String dateExpiredKey,
      Task.Status status, String statusKey) {}

  private final TaskRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Params params) {
    var failure = new InvalidValues();

    var id = Id.create(params.id);
    if (id.isLeft()) {
      failure.invalidValues().put(id.getLeft(), params.idKey);
    }

    return failure.invalidValues().size() == 0
        ? repository.update(
          id.get(),
          params.name,
          params.type,
          params.description,
          params.authorId,
          params.executorId,
          params.dateExpired,
          params.status
        )
        : Results.fail(failure);
  }
}
