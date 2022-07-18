package com.htc.domain.usecases.task;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий удаления задачи по его идентификатору.
 */
@Component
@AllArgsConstructor
public class DeleteTaskById implements UseCase<DeleteTaskById.Params, Void> {
  public record Params(int id, String key) {}

  private final TaskRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Params params) {
    return null;
  }
}
