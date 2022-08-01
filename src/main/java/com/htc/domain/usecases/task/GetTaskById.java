package com.htc.domain.usecases.task;

import com.htc.domain.entities.Task;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.Results;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения задачи по её идентификатору.
 */
@Component
@AllArgsConstructor
public class GetTaskById implements UseCase<GetTaskById.Params, Task> {
  /**
   * Параметры сценария поиска задачи по идентификатору.
   *
   * @param id Идентификатор задачи.
   * @param idKey Ключ идентификатора задачи.
   */
  public record Params(int id, String idKey) {}

  private final TaskRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Params params) {
    var id = Id.create(params.id);
    if (id.isRight()) {
      return repository.get(id.get());
    }

    var failure = new InvalidValues();
    failure.invalidValues().put(id.getLeft(), params.idKey);
    return Results.fail(failure);
  }
}
