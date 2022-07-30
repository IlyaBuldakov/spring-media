package com.htc.domain.usecases.contents;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.Unauthorized;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.ContentsRepository;
import com.htc.domain.usecases.UseCaseHelper;
import com.htc.util.FileHelper;
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
public class CreateContent {

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
      return CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
    var format = FileHelper.getContentFormat(file, fileName);
    if (format.isLeft()) {
      return CompletableFuture.completedFuture(Either.left(format.getLeft()));
    }
    return UseCaseHelper.hasRolePermissions(permissions, permittedRoles)
            ? contentsRepository.create(authorId, fileName, ContentType.PHOTO,
            (Content.ContentFormat) format.get(), url, Integer.parseInt(taskId))
            : CompletableFuture.completedFuture(Either.left(Unauthorized.FORBIDDEN));
  }
}
