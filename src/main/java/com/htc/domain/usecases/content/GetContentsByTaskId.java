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
 * Сценарий получения контента по его идентификатору.
 */
@Component
@AllArgsConstructor
public class GetContentsByTaskId implements UseCase<Integer, Iterable<Content>> {
  private final ContentRepository repository;

  @Override
  public Future<Either<Failure, Iterable<Content>>> execute(Integer taskId) {
    return repository.getContentsByTaskId(taskId);
  }
}