package com.htc.domain.usecases.content;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий добавления нового контента в задачу.
 */
//@Component
@AllArgsConstructor
public final class AddContent implements UseCase<Content, Content> {
  private final ContentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Content>> execute(Content content) {
    return repository.add(content);
  }
}
