package com.htc.domain.usecases.file;

import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.file.File.FileFormat;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.FilesRepository;
import com.htc.domain.usecases.UseCaseHelper;
import com.htc.util.FileHelper;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.io.File;
import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария загрузки файла в базу данных.
 */
@Component
@AllArgsConstructor
public class UploadFile {

  FilesRepository filesRepository;

  /**
   * Роли, которым разрешено данное действие.
   */
  private final Role[] permittedRoles = new Role[] {
          Role.ADMIN,
          Role.MANAGER
  };

  /**
   * Метод сценария.
   *
   * @param fileName    Имя файла.
   * @param composedUrl Составной URL.
   * @param dateCreated Дата создания файла.
   * @param taskId      Идентификатор задачи.
   * @param file        Файл.
   * @return void.
   */
  public CompletableFuture<Either<Failure, Void>> execute(Set<String> permissions, String fileName,
                                                          String composedUrl, LocalDate dateCreated,
                                                          String taskId, File file) {
    var expectedFailure = ValuesValidator.validateStringId(taskId);
    if (expectedFailure != null) {
      return CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
    var format = FileHelper.getFileFormat(file, fileName);
    if (format.isLeft()) {
      return CompletableFuture.completedFuture(Either.left(format.getLeft()));
    }
    return UseCaseHelper.hasRolePermissions(permissions, permittedRoles)
            ? CompletableFuture.completedFuture(Either.left(format.getLeft()))
            : filesRepository.uploadFile(fileName, dateCreated,
            (FileFormat) format.get(), composedUrl, Integer.parseInt(taskId));
  }
}