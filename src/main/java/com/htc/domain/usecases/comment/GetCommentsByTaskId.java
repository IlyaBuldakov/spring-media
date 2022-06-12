package com.htc.domain.usecases.comment;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения комментария по его идентификатору.
 */
@Component
@AllArgsConstructor
public class GetCommentsByTaskId implements UseCase<Integer, Iterable<Comment>> {
  private final CommentRepository repository;

  @Override
  public Future<Either<Failure, Iterable<Comment>>> execute(Integer taskId) {
    return repository.getCommentsByTaskId(taskId);
  }
}
