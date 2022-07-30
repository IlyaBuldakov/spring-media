package com.htc.domain.repositories;

import com.htc.domain.entities.failure.Failure;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий для работы с комментариями.
 */
public interface CommentsRepository {

  /**
   * Создание комментария.
   *
   * @param taskId   Идентификатор задачи.
   * @param authorId Идентификатор автора.
   * @param message  Сообщение комментария.
   * @return void.
   */
  CompletableFuture<Either<Failure, Void>> createComment(int taskId, int authorId, String message);
}
