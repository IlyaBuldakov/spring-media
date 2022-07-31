package com.htc.domain.usecases;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.repositories.UserRepository;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Сценарии использования задач.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TaskUseCase {

  /**
   * Сценарий создания задачи.
   */
  public static final class Create extends BaseUseCase<Create.Params, Task> {
    private final TaskRepository repository;

    @Override
    protected Set<User.Role> getPermittedRoles() {
      return EnumSet.of(User.Role.ADMIN, User.Role.MANAGER);
    }


    @Override
    public Either<Failure, Task> execute(Params params) {
      LocalDateTime dateCreated = LocalDateTime.now();
      Task.Status status = Task.Status.IN_WORK;
      return Either.right(
              repository.create(
                      params.name,
                      params.contentType,
                      params.description,
                      params.author,
                      params.executor,
                      dateCreated,
                      params.dateExpired,
                      status));

    }

    public Create(@NonNull TaskRepository repository,
                  @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }

    /**
     * Параеметры выполнения сценария создания задачи.
     *
     * @param name Название задачи.
     * @param contentType Тип медиаконтекта.
     * @param description Описание задачи.
     * @param author Автор задачи.
     * @param executor Исполнитель задачи.
     * @param dateExpired Дата окончания срока выполнения задачи.
     */

    public record Params(
            Task.Name name,
            Content.Type contentType,
            Task.Description description,
            User author,
            User executor,
            LocalDateTime dateExpired) {
    }
  }

  /**
   * Сценарий удаления задачи по его идентификатору.
   */
  public static final class DeleteById extends BaseUseCase<Id, Void> {
    private final TaskRepository repository;

    @Override
    public Set<User.Role> getPermittedRoles() {
      return EnumSet.of(User.Role.ADMIN);
    }

    /**
     * <p>Удаляет задачу по ее идентификатору.</p>
     *
     * <p>Перед удалением проверяет, что задача действительно существует.</p>
     *
     * @param id Идентификатор задачи.
     * @return Ошибка или ничего.
     */
    @Override
    public Either<Failure, Void> execute(@NonNull Id id) {
      if (repository.exists(id)) {
        return Either.left(new UserUseCase.NotFound());
      }
      repository.delete(id);
      return Either.right(null);
    }

    public DeleteById(@NonNull TaskRepository repository,
                      @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }
  }

  /**
   * Сценарий получения списка всех задач.
   */
  public static final class GetAll extends BaseUseCase<UseCase.NoParams, Collection<Task>> {
    private final TaskRepository repository;

    @Override
    protected Set<User.Role> getPermittedRoles() {
      return null;
    }

    @Override
    public Either<Failure, Collection<Task>> execute(@NonNull NoParams param) {
      return Either.right(repository.getAll());
    }

    public GetAll(@NonNull TaskRepository repository,
                  @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }
  }

  /**
   * Сценарий получения задачи по ее идентификатору.
   */
  public static final class GetById extends BaseUseCase<Id, Task> {
    private final TaskRepository repository;

    @Override
    protected Set<User.Role> getPermittedRoles() {
      return EnumSet.of(User.Role.ADMIN, User.Role.MANAGER);
    }

    /**
     * Получает задачу по ее идентификатору.
     *
     * @param targetId Идентификатор пользователя.
     * @return Ошибка или пользователь.
     */
    @Override
    public Either<Failure, Task> execute(@NonNull Id targetId) {
      final var task = repository.get(targetId);
      if (task.isEmpty()) {
        return Either.left(new UserUseCase.NotFound());
      }

      return Either.right(task.get());
    }

    public GetById(@NonNull TaskRepository repository,
                   @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }
  }

  /**
   * Сценарий обновления задачи.
   */
  public static final class Update extends BaseUseCase<Update.Params, Task> {
    private final TaskRepository repository;

    @Override
    protected Set<User.Role> getPermittedRoles() {
      return EnumSet.of(User.Role.ADMIN, User.Role.MANAGER);
    }


    @Override
    public Either<Failure, Task> execute(Params params) {
      if (repository.exists(params.id)) {
        return Either.left(new UserUseCase.NotFound());
      }

      return Either.right(
              repository.update(
                      params.id,
                      params.name,
                      params.contentType,
                      params.description,
                      params.author,
                      params.executor,
                      params.dateExpired));
    }

    public Update(@NonNull TaskRepository repository,
                  @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }

    /**
     * Параметры выполнения сценарий обновления задачи.
     *
     * @param id Идентификатор задачи.
     * @param name Название задачи.
     * @param contentType Тип медиаконтекта.
     * @param description Описание задачи.
     * @param author Автор задачи.
     * @param executor Исполнитель задачи.
     * @param dateExpired Срок задачи.
     */
    public record Params(
            Id id,
            Task.Name name,
            Content.Type contentType,
            Task.Description description,
            User author,
            User executor,
            LocalDateTime dateExpired ) {
    }
  }

  /**
   * Запрошенная задача не найдена.
   */
  public record NotFound() implements Failure {
  }
}
