package com.htc.domain.usecases.contents;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.ContentsRepository;
import com.htc.util.FileHelper;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария создания контента.
 */
@Component
@AllArgsConstructor
public class CreateContent {

  /**
   * Поле для внедрения реализации из infrastructure layer.
   */
  ContentsRepository contentsRepository;

  /**
   * Метод сценария.
   *
   * @param fileName Имя контента.
   * @param taskId   Идентификатор задачи.
   * @return void.
   */
  public CompletableFuture<Either<Failure, Void>> execute(int authorId, String fileName,
                                                          File file, String url, String taskId) {
    var expectedFailure = ValuesValidator.validateStringId(taskId);
    if (expectedFailure != null) {
      return CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
    var format = FileHelper.getContentFormat(file, fileName);
    return format.isLeft() ? CompletableFuture.completedFuture(Either.left(format.getLeft()))
            : contentsRepository.create(authorId, fileName, ContentType.PHOTO,
            (Content.ContentFormat) format.get(), url, Integer.parseInt(taskId));
  }
}
