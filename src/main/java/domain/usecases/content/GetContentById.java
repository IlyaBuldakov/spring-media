package domain.usecases.content;

import domain.entities.content.Content;
import domain.entities.failures.Failure;
import domain.repositories.ContentRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения контента по ее идентификатору.
 */
@AllArgsConstructor
public final class GetContentById implements UseCase<Integer, Content> {
  private final ContentRepository repository;

  @Override
  public Future<Either<Failure, Content>> execute(Integer id) {
    return repository.get(id);
  }
}


