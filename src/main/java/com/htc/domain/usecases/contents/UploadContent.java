package com.htc.domain.usecases.contents;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.Forbidden;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.ContentsRepository;
import com.htc.domain.usecases.UseCaseHelper;
import com.htc.util.FileHelper;
import com.htc.util.Results;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.io.File;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария создания контента.
 */
@Component
@AllArgsConstructor
public class UploadContent {

  ContentsRepository contentsRepository;

  /**
   * Роли, которым разрешено данное действие.
   */
  private final Role[] permittedRoles = new Role[]{
          Role.ADMIN,
          Role.CONTENT_MAKER
  };

  /**
   * Метод сценария.
   *
   * @param fileName Имя контента.
   * @param taskId   Идентификатор задачи.
   * @return void.
   */
  public CompletableFuture<Either<Failure, Void>> execute(int authorId, Set<String> permissions, String fileName,
                                                          File file, String url, String taskId) {
    var expectedFailure = ValuesValidator.validateStringId(taskId);
    if (expectedFailure != null) {
      return Results.fail(expectedFailure);
    }
    var expectedFormat = FileHelper.getContentFormat(file, fileName);
    if (expectedFormat.isLeft()) {
      return Results.fail(expectedFormat.getLeft());
    }
    var format = (Content.ContentFormat) expectedFormat.get();
    var type = FileHelper.getContentType(file);
    return UseCaseHelper.hasRolePermissions(permissions, permittedRoles)
            ? contentsRepository.create(authorId, fileName, type,
            format, url, Integer.parseInt(taskId))
            : Results.fail(new Forbidden());
  }
}
