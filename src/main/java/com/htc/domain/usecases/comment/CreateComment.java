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
 * Сценарий создания комментария.
 */
@Component
@AllArgsConstructor
public class CreateComment implements UseCase<Comment, Comment> {
  private final CommentRepository repository;

  @Override
  public Future<Either<Failure, Comment>> execute(Comment comment) {
    return repository.create(comment);
  }
}
