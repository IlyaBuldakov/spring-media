package finalproject.application.services;

import finalproject.domain.entities.Comment;
import finalproject.domain.entities.content.Content;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Сервис комментариев.
 */
public interface CommentService {

  /**
   * Публикация комментария.
   *
   * @param comment комментарий
   *
   * @return id комментария.
   */
  CompletableFuture<Either<Failure, Comment>> postCommentToTask(
          int userId, int taskId, String comment);


  CompletableFuture<Either<Failure, Void>> deleteCommentById(int taskId, int authorizedUser);
}
