package domain.usecases.comment;

import domain.entities.comments.Comment;
import domain.entities.failures.Failure;
import domain.repositories.CommentRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий обновления комментария.
 */
@AllArgsConstructor
public final class UpdateComment implements UseCase<Comment, Comment> {
  private final CommentRepository repository;

  @Override
  public Future<Either<Failure, Comment>> execute(Comment comment) {
    return repository.update(comment);
  }
}
