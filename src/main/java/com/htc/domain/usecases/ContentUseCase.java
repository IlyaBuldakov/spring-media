package com.htc.domain.usecases;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.util.FileMetadata;
import com.htc.domain.util.FileUploadService;
import com.htc.domain.util.PreviewPicture;
import io.vavr.control.Either;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

/**
 * Сценарии использования медиаконтента.
 */
public class  ContentUseCase {
  /**
   * Сценарий создания контента.
   */
  public static class Create extends BaseUseCase<Create.Params, Content> {
    private final ContentRepository repository;
    private final FileMetadata fileMetadata;
    private final FileUploadService fileUploadService;
    private final PreviewPicture previewPicture;

    @Override
    protected Set<User.Role> getPermittedRoles() {
      return null;
    }

    @Override
    public Either<Failure, Content> execute(Params params) {

      var name = Content.Name.create(params.file.getName());

      LocalDateTime dateCreated = LocalDateTime.now();
      User author = null; //current authenticated user?

      var format = fileMetadata.getContentFormat(params.file);
      if (format.isEmpty()) {
        return Either.left(new NotSupported());
      }
      var type = fileMetadata.getContentType(params.file);

      var url = Content.Url.create("url");

      File preview;
      if (type.isPresent()) {
        preview = previewPicture.createPreview(params.file, type.get());
      }

      var previewUrl = Content.Url.create("preview");

      Task task = null;
        fileUploadService.uploadFile(params.file);
        fileUploadService.uploadFile(params.file);
        return Either.right(
                repository.create(
                        task,
                        type.get(),
                        name.get(),
                        dateCreated,
                        author,
                        format.get(),
                        url.get(),
                        previewUrl.get()));
    }

    /**
     * Создает сценарий создания медиаконтента.
     *
     * @param userRepository Рерозиторий пользователя.
     * @param repository Репозиторий медиаконтента.
     * @param fileMetadata Сервис для определения типа и формата файла.
     * @param fileUploadService СЕрвис для загрузки файлов на сервер.
     * @param previewPicture Сервис для создания миниатюры.
     */
    public Create(
            @NonNull UserRepository userRepository,
            @NonNull ContentRepository repository,
            @NonNull FileMetadata fileMetadata,
            @NonNull FileUploadService fileUploadService,
            @NonNull PreviewPicture previewPicture) {
      super(userRepository);
      this.repository = repository;
      this.fileMetadata = fileMetadata;
      this.fileUploadService = fileUploadService;
      this.previewPicture = previewPicture;
    }

    /**
     * Параметры выполнения сценария.
     *
     * @param file Файл.
     * @param taskId Идентификатор задачи.
     */
    public record Params(
            MultipartFile file,
            Id taskId,
            Id userId) {
    }
  }

  /**
   * Сценарий удаления контента по его идентификатору.
   */
  public static final class DeleteById extends BaseUseCase<Id, Void> {
    private final ContentRepository repository;

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



    public DeleteById(@NonNull ContentRepository repository,
                      @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }
  }

  /**
   * Сценарий получения медиакотента по запросу.
   */
  public static final class GetFeed
          extends BaseUseCase<GetFeed.Params, Collection<Content>> {
    private final ContentRepository repository;

    @Override
    protected Set<User.Role> getPermittedRoles() {
      return null;
    }

    @Override
    public Either<Failure, Collection<Content>> execute(
            Params param) {
      return Either.right(repository.getFeedByQuery(
              param.page,
              param.count,
              param.author,
              param.date,
              param.typeId));
    }

    public GetFeed(@NonNull ContentRepository repository,
                   @NonNull UserRepository userRepository) {
      super(userRepository);
      this.repository = repository;
    }

    /**
     * Параметры для выполнения сценария.
     *
     * @param page Номер страницы летны медиакотента.
     * @param count Число элементов на станице летны медиакотента.
     * @param author Автор медиакотента.
     * @param date Дата публикации медиакотента.
     * @param typeId Тип медиакотента.
     */
    public record Params(
            Integer page,
            Integer count,
            String author,
            LocalDate date,
            Integer typeId) {
    }
  }
  /**
   * Запрошенный медиакотент не найден.
   */
  public record NotFound() implements Failure {
  }

  /**
   * Формат файла медиаконтента не поддерживается.
   */
  public record NotSupported() implements Failure {
  }
}
