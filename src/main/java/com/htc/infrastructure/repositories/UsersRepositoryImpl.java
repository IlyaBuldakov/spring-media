package com.htc.infrastructure.repositories;

import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.NotFound;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.infrastructure.jpa.UsersJpaRepository;
import com.htc.infrastructure.mappers.UserMapper;
import com.htc.util.Results;
import io.vavr.control.Either;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Реализация репозитория пользователей.
 */
@Component
@AllArgsConstructor
public class UsersRepositoryImpl implements UsersRepository {

  UsersJpaRepository usersJpaRepository;

  /**
   * Создание пользователя, шифрование пароля.
   *
   * @param name     Имя пользователя.
   * @param password Пароль пользователя.
   * @param email    E-mail пользователя.
   * @param avatar   Аватар пользователя.
   * @param role     Роль пользователя.
   * @return Пользователь.
   */
  @Override
  public CompletableFuture<Either<Failure, Void>> create(String name,
                                                         String password,
                                                         String email,
                                                         String avatar,
                                                         Role role) {
    BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
    usersJpaRepository.save(
            new UserMapper(name, bcryptEncoder.encode(password), email, avatar, role));
    return Results.nullValue();
  }

  /**
   * Получение пользователя по идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @return Пользователь.
   */
  @Override
  public CompletableFuture<Either<Failure, User>> getById(int id) {
    var user = usersJpaRepository.findById(id);
    if (user.isPresent()) {
      return Results.success(user.get());
    }
    return Results.fail(NotFound.USER);
  }

  /**
   * Получение пользователя по E-mail.
   *
   * @param email E-mail пользователя.
   * @return Пользователь.
   */
  @Override
  public CompletableFuture<Either<Failure, User>> getByEmail(String email) {
    var user = usersJpaRepository.findUserMapperByEmail(email);
    if (user.isPresent()) {
      return Results.success(user.get());
    }
    return Results.fail(NotFound.USER);
  }

  /**
   * Получение списка всех пользователей.
   *
   * @return Список пользователей.
   */
  @Override
  public CompletableFuture<Either<Failure, List<User>>> getAll() {
    return Results.success(new ArrayList<>(usersJpaRepository.findAll()));
  }

  /**
   * Проверка на то, существует ли пользователь
   * по заданному адресу электронной почты.
   *
   * @return boolean.
   */
  @Override
  public boolean userExistsByEmail(String email) {
    return usersJpaRepository.findUserMapperByEmail(email).isPresent();
  }

  /**
   * Обновление пользователя.
   *
   * @param id       Идентификатор пользователя.
   * @param name     Имя пользователя.
   * @param password Пароль пользователя.
   * @param email    E-mail пользователя.
   * @param avatar   Аватар пользователя.
   * @param role     Роль пользователя.
   * @return Обновлённый пользователь.
   */
  @Override
  public CompletableFuture<Either<Failure, Void>> update(int id,
                                                         String name,
                                                         String password,
                                                         String email,
                                                         String avatar,
                                                         Role role) {
    usersJpaRepository.save(new UserMapper(id, name, password, email, avatar, role));
    return Results.nullValue();
  }

  /**
   * Удаление пользователя.
   *
   * @param id Идентификатор пользователя.
   * @return Удалённый пользователь.
   */
  @Override
  public CompletableFuture<Either<Failure, Void>> deleteById(int id) {
    try {
      usersJpaRepository.deleteById(id);
    } catch (EmptyResultDataAccessException exception) {
      return Results.fail(NotFound.USER);
    }
    return Results.nullValue();
  }
}
