package com.htc.domain.repositories;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий для комментария.
 */
public interface CommentRepository {
  /**
   * Добавление нового комментария в задачу.
   *
   * @param dateCreated дата создания
   * @param author автор
   * @param message текст комментария
   * @param content контент (набор)
   * @return comment новый комментарий, подробнее {@link Comment}
   */
  //TODO реализовать список Content
  CompletableFuture<Either<Failure, Comment>> add(DateCreated dateCreated,
                                                  User author,
                                                  String message,
                                                  int[] content);

  /**
   * Получение комментария по идентификатору.
   *
   * @param commentId идентификатор комментария
   * @return comment комментарий, подробнее {@link Comment}
   */
  CompletableFuture<Either<Failure, Comment>> get(Id commentId);

  /**
   * Удаление комментария по идентификатору.
   *
   * @param id идентификатор комментария
   */
  CompletableFuture<Either<Failure, Void>> delete(Id id);
}
