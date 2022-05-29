package com.example.mediacontentsystem.domain.usecases.user;

import com.example.mediacontentsystem.domain.entities.failures.Failure;
import com.example.mediacontentsystem.domain.entities.user.RoleType;
import com.example.mediacontentsystem.domain.entities.user.User;
import com.example.mediacontentsystem.domain.repositories.UserRepository;
import com.example.mediacontentsystem.domain.usecases.UseCaseUsingParams;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Фильтрует пользователей по имени и роли.
 */
@AllArgsConstructor
public final class FilterUsersByNameAndRole
    implements UseCaseUsingParams<FilterUsersByNameAndRole.Query, Iterable<User>> {
  private final UserRepository repository;

  /**
   * Параметры запроса.
   */
  @AllArgsConstructor
  public static class Query {
    /**
     * Параметры запроса.
     *
     * @return name Имя пользователя.
     */
    private @Getter String name;

    /**
     * Параметры запроса.
     *
     * @return role Роль пользователя.
     */
    private @Getter RoleType role;
  }

  @Override
  public Future<Either<Failure, Iterable<User>>> execute(Query param) {
    return repository.filter(param.getName(), param.getRole());
  }
}
