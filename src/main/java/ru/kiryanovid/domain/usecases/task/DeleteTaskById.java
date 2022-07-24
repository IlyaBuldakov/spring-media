package ru.kiryanovid.domain.usecases.task;

import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.repositories.TaskRepositories;
import ru.kiryanovid.domain.usecases.UseCase;

/**
 * Удаление пользователя по идентификатору.
 */
@Component
public final class DeleteTaskById implements UseCase<Integer, Void> {
  private final TaskRepositories repositories;

  public DeleteTaskById(TaskRepositories repositories) {
    this.repositories = repositories;
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Integer param) {
    repositories.delete(param);
    return null;
  }
}
