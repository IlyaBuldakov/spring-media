package com.htc.domain.usecases.contents;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.Unauthorized;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.ContentsRepository;
import com.htc.domain.usecases.UseCaseHelper;
import io.vavr.control.Either;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария получения всего контента.
 */
@Component
@AllArgsConstructor
public class GetAllContent {

  ContentsRepository contentsRepository;

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
   * @return void.
   */
  public CompletableFuture<Either<Failure, List<Content>>> execute(Set<String> permissions) {
    return UseCaseHelper.hasRolePermissions(permissions, permittedRoles)
            ? contentsRepository.getAll()
            : CompletableFuture.completedFuture(Either.left(Unauthorized.FORBIDDEN));
  }
}
