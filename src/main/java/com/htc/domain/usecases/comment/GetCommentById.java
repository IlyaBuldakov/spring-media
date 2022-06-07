package com.htc.domain.usecases.comment;

import com.htc.domain.entities.comments.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения комментария по ее идентификатору.
 */
@AllArgsConstructor
public final class GetCommentById implements UseCase<Integer, Comment> {
  private final CommentRepository repository;

  @Override
  public Future<Either<Failure, Comment>> execute(Integer id) {
    return repository.get(id);
  }
}


