package com.htc.domain.usecases.task;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения задачи по идентификатору.
 */
@AllArgsConstructor
public final class GetTaskById implements UseCase<GetTaskById.Params, Task> {
  /**
   * Параметры сценария получения задачи по идентификатору.
   *
   * @param id идентификатор задачи
   */
  public record Params(Id id) {}

  private final TaskRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Params params) {
    return repository.get(params.id());
  }
}
