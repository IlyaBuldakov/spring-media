package com.htc.domain.repositories;

import com.htc.domain.entities.Comment;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий комментариев.
 */
public interface CommentRepository {

  /**
   * Создаёт комментарий.
   *
   * @param dateCreateComment Дата создания комментария.
   * @param userId Пользователь создавший комментарий.
   * @param commentMessage Текст комментария.
   * @param taskId Задача в которой оставлен комментарий.
   * @return Комментарий или ошибка.
   */
  CompletableFuture<Either<Failure, Comment>> create(
      LocalDateTime dateCreateComment,
      int userId,
      int taskId,
      String commentMessage
  );

  /**
   * Получает комментарии по идентификатору задачи.
   *
   * @param taskId Идентификатор задачи.
   * @return Комментарии по идентификатору задачи.
   */
  CompletableFuture<Either<Failure, Collection<Comment>>> getCommentsByTaskId(int taskId);
}
