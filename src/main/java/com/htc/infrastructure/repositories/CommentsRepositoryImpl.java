package com.htc.infrastructure.repositories;

import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.NotFound;
import com.htc.domain.repositories.CommentsRepository;
import com.htc.infrastructure.jpa.CommentsJpaRepository;
import com.htc.infrastructure.jpa.TasksJpaRepository;
import com.htc.infrastructure.mappers.CommentMapper;
import com.htc.util.Results;
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

  TasksJpaRepository tasksJpaRepository;

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
    if (tasksJpaRepository.existsById(taskId)) {
      commentsJpaRepository.save(new CommentMapper(taskId, LocalDate.now(), authorId, message));
      return Results.nullValue();
    }
    return Results.fail(NotFound.TASK);
  }
}
