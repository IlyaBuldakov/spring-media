package com.htc.domain.usecases.content;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий создания контента.
 */
@AllArgsConstructor
public class CreateContent implements UseCase<Content, Content> {
  private final ContentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Content>> execute(Content content) {
    return repository.create(content);
  }
}