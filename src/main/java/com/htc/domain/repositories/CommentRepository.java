package com.htc.domain.repositories;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
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
  //TODO Content
  CompletableFuture<Either<Failure, Comment>> add(DateCreated dateCreated,
                                                  User author,
                                                  String message,
                                                  int[] content);

  /**
   * Получение комментария по идентификатору.
   *
   * @param id идентификатор комментария
   * @return comment комментарий, подробнее {@link Comment}
   */
  CompletableFuture<Either<Failure, Comment>> get(int id);

  /**
   * Удаление комментария по идентификатору.
   *
   * @param id идентификатор комментария
   */
  CompletableFuture<Either<Failure, Void>> delete(int id);
}
