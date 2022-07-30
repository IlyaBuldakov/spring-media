package com.htc.domain.usecases.comments;

import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.Unauthorized;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.CommentsRepository;
import com.htc.domain.usecases.UseCaseHelper;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.util.Set;
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
   * Роли, которым разрешено данное действие.
   */
  private final Role[] permittedRoles = new Role[] {
          Role.ADMIN,
          Role.MANAGER,
          Role.CONTENT_MAKER
  };

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
                                                          Set<String> permissions,
                                                          String message) {
    var expectedFailure = ValuesValidator.validateStringId(taskId);
    if (expectedFailure != null) {
      return CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
    return UseCaseHelper.hasRolePermissions(permissions, permittedRoles)
            ? commentsRepository.createComment(Integer.parseInt(taskId), authorId, message)
            : CompletableFuture.completedFuture(Either.left(Unauthorized.FORBIDDEN));
  }
}
