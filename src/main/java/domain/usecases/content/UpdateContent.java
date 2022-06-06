package domain.usecases.content;

import domain.entities.content.Content;
import domain.entities.failures.Failure;
import domain.repositories.ContentRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий обновления контента.
 */
@AllArgsConstructor
public final class UpdateContent implements UseCase<Content, Content> {
  private final ContentRepository repository;

  @Override
  public Future<Either<Failure, Content>> execute(Content content) {
    return repository.update(content);
  }
}
