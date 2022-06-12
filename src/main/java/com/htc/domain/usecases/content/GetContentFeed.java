package com.htc.domain.usecases.content;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения списка всех пользователей.
 */
@Component
@AllArgsConstructor
public class GetContentFeed implements UseCase<Void, Iterable<Content>> {
  private final ContentRepository repository;

  @Override
  public Future<Either<Failure, Iterable<Content>>> execute(Void param) {
    return repository.getContentFeed();
  }
}