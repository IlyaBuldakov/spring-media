package com.htc.domain.usecases;

import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.UserRepository;
import io.vavr.control.Either;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * Базовая реализация сценария использования.
 *
 * @param <ParamsT> Параметры сценария использования.
 * @param <ResultT> Результат выполнения сценария использования.
 */
@AllArgsConstructor
public abstract class BaseUseCase<ParamsT, ResultT> implements UseCase<ParamsT, ResultT> {
  protected final UserRepository userRepository;

  /**
   * Возвращает множество ролей пользователей, у которых есть право на выполнение
   * данного сценария использования.
   *
   * @return Множество допустимых ролей пользователей.
   */
  protected abstract Set<User.Role> getPermittedRoles();

  @Override
  public Either<Failure, ResultT> execute(
          @NonNull Id subjectId,
          @NonNull ParamsT params
  ) {
    final var user = this.userRepository.get(subjectId);
    if (user.isEmpty()) {
      return Either.left(new SubjectNotFound());
    }

    final var role = user.get().role();
    final var hasAccess = this.getPermittedRoles().contains(role);
    if (!hasAccess) {
      return Either.left(new NoAccess());
    }

    return this.execute(params);
  }

  /**
   * Аутентифицированный пользователь не найден.
   */
  public record SubjectNotFound() implements Failure {
  }

  /**
   * Недостаточно прав для выполнения операции.
   */
  public record NoAccess() implements Failure {
  }
}
