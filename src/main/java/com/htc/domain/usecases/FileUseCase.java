package com.htc.domain.usecases;

import com.htc.domain.entities.File;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.repositories.UserRepository;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Сценарии использования файлов.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUseCase {

  /**
   * Сценарий создания файла.
   */
  public static final class Create extends BaseUseCase<Create.Params, File> {

    private final FileRepository repository;

    @Override
    protected Set<User.Role> getPermittedRoles() {
      return null;
    }

    @Override
    public Either<Failure, File> execute(Params params) {

      return Either.right(
              repository.create(
                      params.name,
                      LocalDateTime.now(),
                      params.format,
                      params.url,
                      params.task));
    }

    public Create(@NonNull FileRepository repository,
                  @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }

    /**
     * Параметры выполнеия сценария.
     *
     * @param name Имя файла.
     * @param dateCreated Дата создания файла.
     * @param format Формат файла.
     * @param url Адрес файла.
     * @param task Индентификатор родительской задачи.
     */
    public record Params(
            File.Name name,
            LocalDateTime dateCreated,
            File.Format format,
            File.Url url,
            Task task) {
    }
  }

  /**
   * Сценарий удаления задачи по его идентификатору.
   */
  public static final class DeleteById extends BaseUseCase<Id, Void> {

    private final FileRepository repository;

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

    public DeleteById(@NonNull FileRepository repository,
                      @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }
  }

  /**
   * Сценарий получения задачи по ее идентификатору.
   */
  public static final class GetById extends BaseUseCase<Id, File> {
    private final FileRepository repository;

    @Override
    protected Set<User.Role> getPermittedRoles() {
      return null;
    }

    @Override
    public Either<Failure, File> execute(@NonNull Id id) {
      final var file = repository.get(id);
      if (file.isEmpty()) {
        return Either.left(new UserUseCase.NotFound());
      }

      return Either.right(file.get());
    }

    public GetById(@NonNull FileRepository repository,
                   @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }
  }
}
