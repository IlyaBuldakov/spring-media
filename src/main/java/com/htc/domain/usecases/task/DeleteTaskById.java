package com.htc.domain.usecases.task;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления задачи по идентификатору.
 */
@AllArgsConstructor
public final class DeleteTaskById implements UseCase<DeleteTaskById.Params, Void> {
  //TODO реализовать каскадное удаление компонентов
  /**
   * Параметры сценария удаления задачи по идентификатору.
   *
   * @param id идентификатор задачи
   */
  public record Params(Id id) {}

  private final TaskRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Params params) {
    return repository.delete(params.id());
  }
}
