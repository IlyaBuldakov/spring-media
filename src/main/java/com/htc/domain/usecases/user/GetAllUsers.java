package com.htc.domain.usecases.user;

import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.Forbidden;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCaseHelper;
import com.htc.util.Results;
import io.vavr.control.Either;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация сценария получения всех пользователей.
 */
@AllArgsConstructor
@Component
public class GetAllUsers {

  private UsersRepository usersRepository;

  /**
   * Роль, которой разрешено данное действие.
   */
  private final Role permittedRole = Role.ADMIN;

  /**
   * Метод сценария.
   *
   * @return Список пользователей.
   */
  public CompletableFuture<Either<Failure, List<User>>> execute(Set<String> permissions) {
    return UseCaseHelper.hasRolePermissions(permissions, permittedRole)
            ? usersRepository.getAll()
            : Results.fail(new Forbidden());
  }
}
