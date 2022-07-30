package com.htc.domain.service;

import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.usecases.UseCase;
import com.htc.domain.usecases.UserUseCase;
import io.vavr.control.Either;
import java.util.Collection;
import lombok.AllArgsConstructor;

/**
 * Фасад для сценариев работы с пользователями.
 */
@AllArgsConstructor
public final class UserService {
  private UserUseCase.Create create;
  private UserUseCase.UpdateById updateById;
  private UserUseCase.DeleteById deleteById;
  private UserUseCase.GetById getById;
  private UserUseCase.GetByEmail getByEmail;
  private UserUseCase.GetAll getAll;

  /**
   * Создаёт пользователя.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param name Имя пользователя.
   * @param email Электронная почта пользователя.
   * @param password Пароль пользователя.
   * @param image Изображение пользователя.
   * @param role Роль пользователя.
   * @return Ошибка или пользователь.
   */
  public Either<Failure, User> create(
          Id subjectId,
          User.Name name,
          User.Email email,
          User.Password password,
          User.Image image,
          User.Role role
  ) {
    return this.create.execute(
            subjectId,
            new UserUseCase.Create.Params(name, email, password, image, role)
    );
  }

  /**
   * Обновляет данные пользователя.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param id Идентификатор пользователя.
   * @param name Имя пользователя.
   * @param email Электронная почта пользователя.
   * @param password Пароль пользователя.
   * @param image Изображение пользователя.
   * @param role Роль пользователя.
   * @return Ошибка или пользователь.
   */
  public Either<Failure, User> update(
          Id subjectId,
          Id id,
          User.Name name,
          User.Email email,
          User.Password password,
          User.Image image,
          User.Role role
  ) {
    return this.updateById.execute(
            subjectId,
            new UserUseCase.UpdateById.Params(id, name, email, password, image, role)
    );
  }

  /**
   * Удаляет пользователя по его идентификатору.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param targetId Идентификатор пользователя.
   * @return Ошибка или ничего.
   */
  public Either<Failure, Void> delete(Id subjectId, Id targetId) {
    return this.deleteById.execute(subjectId, targetId);
  }

  /**
   * Получает пользователя по его идентификатору.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @param targetId Идентификатор пользователя.
   * @return Ошибка или пользователь.
   */
  public Either<Failure, User> get(Id subjectId, Id targetId) {
    return this.getById.execute(subjectId, targetId);
  }

  /**
   * При совпадении пароля получает пользователя по его электронной почте.
   *
   * @param email Электронная почта пользователя.
   * @param password Пароль пользователя
   * @return Ошибка или пользователь.
   */
  public Either<Failure, User> get(User.Email email, User.Password password) {
    return this.getByEmail.execute(
            new UserUseCase.GetByEmail.Params(email, password)
    );
  }

  /**
   * Получает список всех пользователей.
   *
   * @param subjectId Идентификатор пользователя, выполняющего данную операцию.
   * @return Ошибка или список пользователей.
   */
  public Either<Failure, Collection<User>> getAll(Id subjectId) {
    return this.getAll.execute(
            subjectId,
            new UseCase.NoParams());
  }
}
