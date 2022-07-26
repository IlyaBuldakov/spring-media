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
   */
  public record Params(
      String name, String nameKey,
      Content.Type type, String typeKey,
      String description, String descriptionKey,
      int authorId, String authorIdKey,
      int executorId, String executorIdKey,
      LocalDateTime dateExpired, String dateExpiredKey,
      Task.Status status, String statusKey
  ) {}

  private final TaskRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Params params) {
    var dateCreated = LocalDateTime.now();
    return repository.create(
        params.name(),
        params.type(),
        params.description(),
        params.authorId(),
        params.executorId,
        dateCreated,
        params.dateExpired,
        params.status
    );
  }
}
