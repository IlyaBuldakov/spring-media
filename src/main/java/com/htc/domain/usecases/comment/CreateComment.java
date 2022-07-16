package com.htc.domain.usecases.comment;

import com.htc.domain.entities.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.Results;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий создания комментария.
 */
@Component
@AllArgsConstructor
public final class CreateComment implements UseCase<CreateComment.Params, Comment> {

  /**
   * Параметры сценария создания комментария.
   *
   * @param dateCreateComment Дата создания комментария.
   * @param userId Пользователь создавший комментарий.
   * @param taskId Задача содержащая комментарий.
   * @param commentMessage Сообщение комментария.
   */
  public record Params(
      LocalDateTime dateCreateComment,
      int userId,
      int taskId,
      String commentMessage) {}

  private final CommentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Comment>> execute(Params params) {
    var failure = new InvalidValues();

    return failure.invalidValues().size() == 0
        ? repository.create(
          params.dateCreateComment,
          params.userId,
          params.taskId,
          params.commentMessage)
        : Results.fail(failure);
  }
}
