package com.htc.domain.usecases.file;

import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.Forbidden;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.FilesRepository;
import com.htc.domain.usecases.UseCaseHelper;
import com.htc.util.Results;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.io.File;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария удаления файла.
 */
@Component
@AllArgsConstructor
public class DeleteFileById {

  FilesRepository filesRepository;

  /**
   * Роли, которым разрешено данное действие.
   */
  private final Role[] permittedRoles = new Role[]{
          Role.ADMIN
  };

  /**
   * Уточняющий элемент пути.
   */
  private static final String DIRECTORY_QUALIFIER = "src/main/webapp/";

  /**
   * Метод сценария.
   *
   * @param fileId Идентификатор файла.
   * @return void
   */
  public CompletableFuture<Either<Failure, Void>> execute(Set<String> permissions,
                                                          String fileId) {
    var expectedFailure = ValuesValidator.validateStringId(fileId);
    if (expectedFailure != null) {
      return Results.fail(expectedFailure);
    }
    var removeFile = removeFile(fileId);
    if (removeFile != null && removeFile.isLeft()) {
      Results.fail(removeFile.getLeft());
    }
    return UseCaseHelper.hasRolePermissions(permissions, permittedRoles)
            ? filesRepository.deleteFile(Integer.parseInt(fileId))
            : Results.fail(new Forbidden());
  }

  /**
   * Метод удаления файла из файловой системы по идентификатору.
   *
   * @param fileId Идентификатор файла.
   * @return void.
   */
  private Either<Failure, Void> removeFile(String fileId) {
    var file = filesRepository.findFileUrlById(Integer.parseInt(fileId));
    if (file.isLeft()) {
      return Either.left(file.getLeft());
    }
    new File(DIRECTORY_QUALIFIER + file.get()).delete();
    return null;
  }
}
