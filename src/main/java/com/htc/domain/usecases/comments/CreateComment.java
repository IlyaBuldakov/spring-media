package com.htc.domain.usecases.comments;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.CommentsRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария создания комментария.
 */
@Component
@AllArgsConstructor
public class CreateComment {

  CommentsRepository commentsRepository;

  /**
   * Метод сценария.
   *
   * @param taskId   Идентификатор задачи.
   * @param authorId Идентификатор автора.
   * @param message  Сообщение комментария.
   * @return void.
   */
  public CompletableFuture<Either<Failure, Void>> execute(String taskId,
                                                          int authorId,
                                                          String message) {
    var expectedFailure = ValuesValidator.validateStringId(taskId);
    return expectedFailure == null
            ? commentsRepository.createComment(Integer.parseInt(taskId), authorId, message)
            : CompletableFuture.completedFuture(Either.left(expectedFailure));
  }
}
