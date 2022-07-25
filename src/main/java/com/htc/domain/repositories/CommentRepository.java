package com.htc.domain.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.Comment;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.Task;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий комментариев.
 */
public interface CommentRepository {

  /**
   * Созает комментарий.
   *
   * @param user    Пользователь - автор комментария.
   * @param task    Задача связаня с комментарием.
   * @param message Собщение комментария.
   * @return Ошибка или комментарий.
   */
  CompletableFuture<Either<Failure, Comment>> create(
          Id user, Id task, Comment.Message message);

  /**
   * Обновляет комментарий.
   *
   * @param comment Комментарий.
   */
  CompletableFuture<Either<Failure, Comment>> update(Comment comment);

  /**
   * Удаляет комментарий.
   *
   * @param id Идентификатор контента.
   */
  CompletableFuture<Either<Failure, Void>> delete(Id id);

  /**
   * Получает список всего комментариев.
   *
   * @return Список комментарев.
   */
  CompletableFuture<Either<Failure, Collection<Comment>>> getAllByTask(Task task);
}
