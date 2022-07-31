package finalproject.application.services;

import finalproject.domain.entities.Comment;
import finalproject.domain.entities.content.Content;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import io.vavr.control.Either;
import java.util.List;
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
  Future<Either<Failure, Integer>> postCommentToTask(Comment comment, int authorizedUser);

  Future<Either<Failure, List<Comment>>> getAllCommentsRelatedToTask(int taskId);

  Future<Either<Failure, List<Comment>>> deleteCommentById(int taskId, int authorizedUser);
}
