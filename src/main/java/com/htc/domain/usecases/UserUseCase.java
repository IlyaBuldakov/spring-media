package com.htc.domain.usecases;

import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.UserRepository;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserUseCase {
  /**
   * Сценарий создания пользователя.
   */
  public static final class Create extends BaseUseCase<Create.Params, User> {
    @Override
    public Set<User.Role> getPermittedRoles() {
      return EnumSet.of(User.Role.ADMIN);
    }

    /**
     * <p>Создаёт пользователя.</p>
     *
     * <p>При создании пользователя происходит проверка на то, что пользователя с такой же
     * электронной почтой не было создано ранее.</p>
     *
     * @param params Параметры сценария создания пользователя.
     * @return Ошибка или пользователь.
     */
    @Override
    public Either<Failure, User> execute(@NonNull Params params) {
      final var emailAlreadyExists = super.userRepository.exists(params.email);
      if (emailAlreadyExists) {
        return Either.left(new EmailAlreadyExists());
      }

      return Either.right(
              super.userRepository.create(
                      params.name,
                      params.email,
                      params.password,
                      params.image,
                      params.role
              )
      );
    }

    public Create(@NonNull UserRepository userRepository) {
      super(userRepository);
    }

    /**
     * Параметры сценария создания пользователя.
     *
     * @param name Имя пользователя.
     * @param email Электронная почта пользователя.
     * @param password Пароль пользователя.
     * @param image Изображение пользователя.
     * @param role Роль пользователя.
     */
    public record Params(
            @NonNull User.Name name,
            @NonNull User.Email email,
            @NonNull User.Password password,
            @NonNull User.Image image,
            @NonNull User.Role role
    ) {
    }
  }

  /**
   * Сценарий обновления данных пользователя.
   */
  public static final class UpdateById extends BaseUseCase<UpdateById.Params, User> {
    @Override
    protected Set<User.Role> getPermittedRoles() {
      return EnumSet.of(User.Role.ADMIN);
    }

    /**
     * <p>Обновляет данные пользователя.</p>
     *
     * <p>При обновления данных пользователя проверяет, что, при изменении
     * электронной почты пользователя, она не будет совпадать с электронной почтой
     * уже существующих пользователей.</p>
     *
     * @param params Параметры сценария обновления данных пользователя.
     * @return Ошибка или пользователь.
     */
    @Override
    public Either<Failure, User> execute(@NonNull Params params) {
      final var user = super.userRepository.get(params.id);
      if (user.isEmpty()) {
        return Either.left(new SubjectNotFound());
      }

      // Проверяем, что, при изменении электронной почты пользователя,
      // она не будет совпадать с электронной почтой уже существующих пользователей.
      final var emailIsChanging = !user.get().getEmail().equals(params.email);
      final var emailAlreadyExists = super.userRepository.exists(params.email);
      if (emailIsChanging && emailAlreadyExists) {
        return Either.left(new EmailAlreadyExists());
      }

      return Either.right(
              super.userRepository.update(
                      params.id,
                      params.name,
                      params.email,
                      params.password,
                      params.image,
                      params.role
              )
      );
    }

    public UpdateById(@NonNull UserRepository userRepository) {
      super(userRepository);
    }

    /**
     * Параметры сценария обновления данных пользователя.
     *
     * @param id Идентификатор пользователя.
     * @param name Имя пользователя.
     * @param email Электронная почта пользователя.
     * @param password Пароль пользователя.
     * @param image Изображение пользователя.
     * @param role Роль пользователя.
     */
    public record Params(
            @NonNull Id id,
            @NonNull User.Name name,
            @NonNull User.Email email,
            @NonNull User.Password password,
            @NonNull User.Image image,
            @NonNull User.Role role
    ) {
    }
  }

  /**
   * Сценарий удаления пользователя по его идентификатору.
   */
  public static final class DeleteById extends BaseUseCase<Id, Void> {

    @Override
    public Set<User.Role> getPermittedRoles() {
      return EnumSet.of(User.Role.ADMIN);
    }

    /**
     * <p>Удаляет пользователя по его идентификатору.</p>
     *
     * <p>Перед удалением проверяет, что искомый пользователь
     * действительно существует.</p>
     *
     * @param targetId Идентификатор пользователя.
     * @return Ошибка или ничего.
     */
    @Override
    public Either<Failure, Void> execute(@NonNull Id targetId) {
      if (!super.userRepository.exists(targetId)) {
        return Either.left(new NotFound());
      }

      super.userRepository.delete(targetId);
      return Either.right(null);
    }

    public DeleteById(@NonNull UserRepository userRepository) {
      super(userRepository);
    }
  }

  /**
   * Сценарий получения пользователя по его идентификатору.
   */
  public static final class GetById extends BaseUseCase<Id, User> {
    @Override
    public Set<User.Role> getPermittedRoles() {
      return EnumSet.of(User.Role.ADMIN, User.Role.MANAGER);
    }

    /**
     * Получает пользователя по его идентификатору.
     *
     * @param targetId Идентификатор пользователя.
     * @return Ошибка или пользователь.
     */
    @Override
    public Either<Failure, User> execute(@NonNull Id targetId) {
      final var user = super.userRepository.get(targetId);
      if (user.isEmpty()) {
        return Either.left(new NotFound());
      }

      return Either.right(user.get());
    }

    public GetById(@NonNull UserRepository userRepository) {
      super(userRepository);
    }
  }

  /**
   * Сценарий получения пользователя по его электронной почте.
   */
  public static final class GetByEmail extends BaseUseCase<GetByEmail.Params, User> {

    @Override
    public Set<User.Role> getPermittedRoles() {
      return EnumSet.of(User.Role.ADMIN, User.Role.MANAGER);
    }

    /**
     * При совпадении пароля получает пользователя по его электронной почте.
     *
     * @param params Параметры сценария получения пользователя по его электронной почте.
     * @return Ошибка или пользователь.
     */
    @Override
    public Either<Failure, User> execute(@NonNull Params params) {
      final var result = super.userRepository.get(params.email);
      if (result.isEmpty()) {
        return Either.left(new NotFound());
      }

      final var user = result.get();
      final var passwordIsValid = user.getPassword().equals(params.password);
      if (!passwordIsValid) {
        return Either.left(new NoAccess());
      }

      return Either.right(user);
    }

    public GetByEmail(@NonNull UserRepository userRepository) {
      super(userRepository);
    }

    /**
     * Параметры сценария получения пользователя по его электронной почте.
     *
     * @param email Электронная почта пользователя.
     * @param password Пароль пользователя
     */
    public record Params(
            @NonNull User.Email email,
            @NonNull User.Password password
    ) {
    }
  }

  /**
   * Сценарий получения списка всех пользователей.
   */
  public static final class GetAll extends BaseUseCase<UseCase.NoParams, Collection<User>> {
    @Override
    protected Set<User.Role> getPermittedRoles() {
      return EnumSet.of(User.Role.ADMIN, User.Role.MANAGER);
    }

    /**
     * Получает список всех пользователей.
     *
     * @param ignored Параметр-заглушка.
     * @return Ошибка или список пользователей.
     */
    @Override
    public Either<Failure, Collection<User>> execute(@NonNull NoParams ignored) {
      return Either.right(super.userRepository.getAll());
    }

    public GetAll(@NonNull UserRepository userRepository) {
      super(userRepository);
    }
  }

  /**
   * Запрошенный пользователь не найден.
   */
  public record NotFound() implements Failure {
  }

  /**
   * Пользователь с указанной электронной почтой уже существует.
   */
  public record EmailAlreadyExists() implements Failure {
  }
}
