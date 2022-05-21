package com.example.mediacontentsystem.domain.repositories;

import com.example.mediacontentsystem.domain.entities.failures.Failure;
import com.example.mediacontentsystem.domain.entities.user.Role;
import com.example.mediacontentsystem.domain.entities.user.User;
import com.example.mediacontentsystem.domain.repositories.base.CrudRepository;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Интерфейс репозитория для пользователей.
 */
public interface UserRepository extends CrudRepository<User> {
  /**
   * Список пользователей по имени.
   *
   * @param name имя пользователя.
   * @return Список пользователей по имени.
   */
  Future<Either<Failure, Iterable<User>>> filter(String name);

  /**
   * Список пользователей по имени и роли.
   *
   * @param name имя пользователя.
   * @param role роль пользователя.
   * @return Список пользователей по имени и роли.
   */
  Future<Either<Failure, Iterable<User>>> filter(String name, Role role);
}
