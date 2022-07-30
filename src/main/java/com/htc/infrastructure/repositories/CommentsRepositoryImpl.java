package com.htc.infrastructure.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.CommentsRepository;
import com.htc.infrastructure.jpa.CommentsJpaRepository;
import com.htc.infrastructure.mappers.CommentMapper;
import io.vavr.control.Either;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация репозитория для работы с комментариями.
 */
@Component
@AllArgsConstructor
public class CommentsRepositoryImpl implements CommentsRepository {

  CommentsJpaRepository commentsJpaRepository;

  /**
   * Создание комментария.
   *
   * @param taskId   Идентификатор задачи.
   * @param authorId Идентификатор автора.
   * @param message  Сообщение комментария.
   * @return void.
   */
  @Override
  public CompletableFuture<Either<Failure, Void>> createComment(int taskId,
                                                                int authorId,
                                                                String message) {
    commentsJpaRepository.save(new CommentMapper(taskId, LocalDate.now(), authorId, message));
    return CompletableFuture.completedFuture(Either.right(null));
  }
}
