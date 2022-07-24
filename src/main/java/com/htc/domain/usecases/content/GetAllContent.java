package com.htc.domain.usecases.content;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения ленты контента.
 */
@AllArgsConstructor
public final class GetAllContent implements UseCase<Void, List<Content>> {
  private final ContentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, List<Content>>> execute(Void param) {
    return repository.getAll();
  }
}
