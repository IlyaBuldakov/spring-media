package com.htc.domain.usecases.comment;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий добавления нового комментария в задачу.
 */
//@Component
@AllArgsConstructor
public final class AddComment implements UseCase<AddComment.Params, Comment> {
  /**
   * Параметры сценария добавления комментария.
   */
  public record Params(User author, String authorKey,
                       String message, String messageKey,
                       int[] content, String contentKey) {}

  private final CommentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Comment>> execute(Params params) {
    var failure = new InvalidValues();
    var dateCreated = DateCreated.create();
    if (dateCreated.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_DATE_CREATED, "Date created");
    }
    //TODO Content
    return failure.getValues().size() == 0
            ? repository.add(dateCreated.get(), params.author(), params.message(), params.content())
            : EitherHelper.badLeft(failure);
  }
}
