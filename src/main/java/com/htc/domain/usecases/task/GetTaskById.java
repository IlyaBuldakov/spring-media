package com.htc.domain.usecases.task;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения задачи по идентификатору.
 */
//@Component
@AllArgsConstructor
public final class GetTaskById implements UseCase<GetTaskById.Params, Task> {
  /**
   * Параметры сценария получения задачи по идентификатору.
   *
   * @param id идентификатор задачи
   * @param key ключ идентификатора задачи
   */
  public record Params(Long id, String key) {}

  private final TaskRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Params params) {
    var id = Id.create(params.id());
    return id.isRight()
            ? repository.get(id.get())
            : EitherHelper.badLeft(new InvalidValues());
  }
}
