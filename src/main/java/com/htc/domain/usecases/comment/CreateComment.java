package com.htc.domain.usecases.comment;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.comments.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий создания комментария.
 */
@Component
@AllArgsConstructor
public class CreateComment implements UseCase<CreateComment.Params, Comment> {

  private final CommentRepository repository;

  /**
   * Параметры выполнения сценария создания комментария.
   *
   * @param user    Пользователь - автор комментария.
   * @param task    Задача под которой остален комментарий.
   * @param message Тест комментария.
   */
  public record Params(
          int user,
          int task,
          String message) {
  }

  @Override
  public CompletableFuture<Either<Failure, Comment>> execute(CreateComment.Params params) {
    var userId = Id.create(params.user);
    var taskId = Id.create(params.task);
    var message = Comment.Message.create(params.message());

    return repository.create(userId.get(), taskId.get(), message.get());
  }

}
