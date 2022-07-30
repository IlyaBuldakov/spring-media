package com.htc.domain.usecases.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.FilesRepository;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария удаления файла.
 */
@Component
@AllArgsConstructor
public class DeleteFile {

  /**
   * Поле для внедрения реализации из infrastructure layer.
   */
  FilesRepository filesRepository;

  /**
   * Метод сценария.
   *
   * @param directoryQualifier Уточняющий элемент URL.
   * @param fileId             Идентификатор файла.
   * @return void
   */
  public CompletableFuture<Either<Failure, Void>> execute(String directoryQualifier,
                                                          String fileId) {
    var expectedFailure = ValuesValidator.validateStringId(fileId);
    if (expectedFailure != null) {
      return CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
    var removeFile = removeFile(directoryQualifier, fileId);
    return (removeFile != null && removeFile.isLeft())
            ? CompletableFuture.completedFuture(Either.left(removeFile.getLeft()))
            : filesRepository.deleteFile(Integer.parseInt(fileId));
  }

  /**
   * Метод удаления файла из файловой системы по идентификатору.
   *
   * @param directoryQualifier Уточняющий элемент пути.
   * @param fileId             Идентификатор файла.
   * @return void.
   */
  private Either<Failure, Void> removeFile(String directoryQualifier, String fileId) {
    var file = filesRepository.findFileUrlById(Integer.parseInt(fileId));
    if (file.isLeft()) {
      return Either.left(file.getLeft());
    }
    new File(directoryQualifier + file.get()).delete();
    return null;
  }
}
