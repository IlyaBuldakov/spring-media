package com.htc.domain.usecases;

import com.htc.domain.entities.Notification;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.repositories.UserRepository;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Сценарии использования уведомлений.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationUseCase {
  /**
   * Сценарий создания уведомления.
   */
  public static class Create
          extends BaseUseCase<Create.Params, Notification> {
    private final NotificationRepository repository;


    @Override
    protected Set<User.Role> getPermittedRoles() {
      return null;
    }

    @Override
    public Either<Failure, Notification> execute(Params params) {
      LocalDateTime dateCreated = LocalDateTime.now();
      return Either.right(repository.create(
              params.type,
              params.date,
              params.message,
              params.user,
              params.task));
    }

    public Create(@NonNull NotificationRepository repository,
                  @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }

    /**
     * Параметры выполнения сценария.
     *
     * @param type Тип уведомления.
     * @param date Дата создания уведомления.
     * @param message Текст уведомления.
     * @param user Пользователь связанный с уведомлением.
     * @param task Задача связанная с уведомлением.
     */
    public record Params(
            Notification.Type type,
            LocalDateTime date,
            Notification.Message message,
            User user,
            Task task) {
    }
  }


  /**
   * Сценарий удаления уведомления по его идентификатору.
   */
  public static final class DeleteById extends BaseUseCase<Id, Void> {

    private final NotificationRepository repository;

    @Override
    protected Set<User.Role> getPermittedRoles() {
      return null;
    }

    @Override
    public Either<Failure, Void> execute(@NonNull Id id) {
      if (repository.exists(id)) {
        return Either.left(new UserUseCase.NotFound());
      }
      repository.delete(id);
      return Either.right(null);
    }

    public DeleteById(
            @NonNull NotificationRepository repository,
            @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }
  }


  /**
   * Сценарий получения списка всех уведомлений задачи.
   */
  public static final class GetAll
          extends BaseUseCase<UseCase.NoParams, Collection<Notification>> {
    private final NotificationRepository repository;

    public GetAll(
            @NonNull NotificationRepository repository,
            @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }

    @Override
    protected Set<User.Role> getPermittedRoles() {
      return null;
    }

    @Override
    public Either<Failure, Collection<Notification>> execute(@NonNull NoParams param) {
      return Either.right(repository.getAll());
    }
  }


  /**
   * Сценарий получения уведомления по ее идентификатору.
   */
  public static final class GetById extends BaseUseCase<Id, Notification> {
    private final NotificationRepository repository;

    @Override
    protected Set<User.Role> getPermittedRoles() {
      return null;
    }

    @Override
    public Either<Failure, Notification> execute(@NonNull Id id) {
      final var notification = repository.get(id);
      if (notification.isEmpty()) {
        return Either.left(new UserUseCase.NotFound());
      }
      return Either.right(notification.get());
    }

    public GetById(@NonNull NotificationRepository repository,
                   @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }
  }
}
