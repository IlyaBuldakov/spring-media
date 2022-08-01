package com.htc.domain.usecases.content;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения ленты контента.
 */
@Component
@AllArgsConstructor
public final class GetContentFeed implements UseCase<GetContentFeed.Params, Collection<Content>> {
  /**
   * Параметры сценария получения ленты контента.
   *
   * @param approve Одобрение контента.
   * @param key Ключ одобрения контента.
   */
  public record Params(boolean approve, String key) {}

  private final ContentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Collection<Content>>> execute(Params params) {
    return repository.getContentFeed(params.approve);
  }
}
