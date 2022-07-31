package com.htc.domain.usecases;

import com.htc.domain.entities.File;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.util.FileMetadata;
import com.htc.domain.util.FileUploadService;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

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
    private final FileMetadata fileMetadata;
    private final FileUploadService fileUploadService;

    @Override
    protected Set<User.Role> getPermittedRoles() {
      return null;
    }

    @Override
    public Either<Failure, File> execute(Params params) {

      var name = File.Name.create(params.file.getName());

      LocalDateTime dateCreated = LocalDateTime.now();

      var format = fileMetadata.getFileFormat(params.file);
      if (format.isEmpty()) {
        return Either.left(new FileUseCase.NotSupported());
      }
      var type = fileMetadata.getContentType(params.file);

      var url = File.Url.create("url");

      Task task = null;
      fileUploadService.uploadFile(params.file);
      return Either.right(
              repository.create(
                      name.get(),
                      LocalDateTime.now(),
                      format.get(),
                      url.get(),
                      task));
    }

    public Create(@NonNull FileRepository repository,
                  @NonNull UserRepository userRepository,
                  @NonNull FileMetadata fileMetadata,
                  @NonNull FileUploadService fileUploadService) {
      super(userRepository);
      this.repository = repository;
      this.fileMetadata = fileMetadata;
      this.fileUploadService = fileUploadService;
    }

    /**
     * Параметры выполнения сценария.
     *
     * @param file файл.
     * @param taskId Идентификатор задачи.
     */
    public record Params(
        MultipartFile file,
        Id taskId) {
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
  public static final class GetAllByTask extends BaseUseCase<Id, File> {
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

    public GetAllByTask(@NonNull FileRepository repository,
                   @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }
  }


  /**
   * Запрошенный файл не найден.
   */
  public record NotFound() implements Failure {
  }

  /**
   * Формат файла не поддерживается.
   */
  public record NotSupported() implements Failure {
  }
}
