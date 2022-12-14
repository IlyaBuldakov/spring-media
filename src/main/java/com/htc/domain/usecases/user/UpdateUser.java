package com.htc.domain.usecases.user;

import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.Forbidden;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCaseHelper;
import com.htc.util.Results;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария обновления пользователя.
 */
@AllArgsConstructor
@Component
public class UpdateUser {

  private UsersRepository usersRepository;

  /**
   * Роль, которой разрешено данное действие.
   */
  private final Role permittedRole = Role.ADMIN;

  /**
   * Метод сценария.
   *
   * @return Пользователь.
   */
  public CompletableFuture<Either<Failure, Void>> execute(Set<String> permissions, String id,
                                                          String name, String password,
                                                          String email, String avatar, Role role) {
    var expectedFailure
            = ValuesValidator.checkUserFields(id, name, password, email, avatar);
    if (expectedFailure != null) {
      return Results.fail(expectedFailure);
    }
    return UseCaseHelper.hasRolePermissions(permissions, permittedRole)
            ? usersRepository.update(Integer.parseInt(id), name, password, email, avatar, role)
            : Results.fail(new Forbidden());
  }
}
