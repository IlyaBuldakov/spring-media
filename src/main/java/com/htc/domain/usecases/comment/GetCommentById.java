package com.htc.domain.usecases.comment;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения комментария по идентификатору.
 */
@AllArgsConstructor
public final class GetCommentById implements UseCase<GetCommentById.Params, Comment> {
  /**
   * Параметры сценария получения комментария.
   *
   * @param id Идентификатор комментария.
   * @param key Ключ идентификатора комментария.
   */
  public record Params(Long id, String key) {}

  private final CommentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Comment>> execute(Params params) {
    var id = Id.create(params.id());
    return id.isRight()
            ? repository.get(id.get())
            : EitherHelper.badLeft(new InvalidValues());
  }
}
