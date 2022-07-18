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
 * Сценарий получения списка всех пользователей.
 */
@Component
@AllArgsConstructor
public final class GetContentFeed implements UseCase<Void, Collection<Content>> {
  private final ContentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Collection<Content>>> execute(Void param) {
    return repository.getContentFeed();
  }
}
