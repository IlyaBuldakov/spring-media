package com.htc.domain.usecases.content;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения контента по его идентификатору.
 */
@AllArgsConstructor
public final class GetContentById implements UseCase<GetContentById.Params, Content> {
  /**
   * Параметры сценария получения контента по его идентификатору.
   *
   * @param id Идентификатор контента.
   * @param key Ключ идентификатора контента.
   */
  public record Params(Long id, String key) {}

  private final ContentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Content>> execute(Params params) {
    var id = Id.create(params.id());
    return id.isRight()
            ? repository.get(id.get())
            : EitherHelper.badLeft(new InvalidValues());
  }
}
