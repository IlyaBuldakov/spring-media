package com.htc.domain.usecases.contents;

import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.Unauthorized;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.ContentsRepository;
import com.htc.domain.usecases.UseCaseHelper;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария удаления контента по идентификатору.
 */
@Component
@AllArgsConstructor
public class DeleteContentById {

  ContentsRepository contentsRepository;

  /**
   * Роли, которым разрешено данное действие.
   */
  private final Role[] permittedRoles = new Role[]{
          Role.ADMIN
  };

  /**
   * Метод сценария.
   *
   * @param id Идентификатор контента
   * @return void.
   */
  public CompletableFuture<Either<Failure, Void>> execute(Set<String> permissions,
                                                          String id) {
    var expectedFailure = ValuesValidator.validateStringId(id);
    if (expectedFailure != null) {
      return CompletableFuture.completedFuture(Either.left(expectedFailure));
    }
    return UseCaseHelper.hasRolePermissions(permissions, permittedRoles)
            ? contentsRepository.deleteById(Integer.parseInt(id))
            : CompletableFuture.completedFuture(Either.left(Unauthorized.FORBIDDEN));
  }
}
