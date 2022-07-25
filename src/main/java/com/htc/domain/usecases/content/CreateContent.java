package com.htc.domain.usecases.content;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.Content;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.User;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.service.FileMetadata;
import com.htc.service.FileUploadService;
import com.htc.service.PreviewPicture;
import io.vavr.control.Either;
import java.io.File;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Сценарий создания контента.
 */
@Component
@AllArgsConstructor
public class CreateContent implements UseCase<CreateContent.Params, Content> {
  private final ContentRepository repository;
  private final FileMetadata fileMetadata;
  private final FileUploadService fileUploadService;
  private final PreviewPicture previewPicture;

  /**
   * Парамерты выполения сценария.
   *
   * @param file Файл.
   * @param taskId Индентификатор задачи.
   */
  public record Params(
          MultipartFile file,
          Id taskId) {
  }

  @Override
  public CompletableFuture<Either<Failure, Content>> execute(Params params) {
    InvalidValues invalidValues = new InvalidValues();

    var type = fileMetadata.getContentType(params.file);
    if (type.isEmpty()) {
      invalidValues.addInvalidValue(InvalidValue.INVALID_CONTENT_TYPE);
    }

    var name = Content.Name.create(params.file.getName());
    if (name.isLeft()) {
      invalidValues.addInvalidValue(name.getLeft());
    }

    LocalDateTime dateCreated = LocalDateTime.now();
    User author = null; //current authenticated user?

    var format = fileMetadata.getContentFormat(params.file);
    if (format.isEmpty()) {
      invalidValues.addInvalidValue(InvalidValue.INVALID_FORMAT);
    }

    var url = Content.Url.create("url");
    if (url.isLeft()) {
      invalidValues.addInvalidValue(url.getLeft());
    }

    File preview;
    if (type.isPresent()) {
      preview = previewPicture.createPreview(params.file, type.get());
    }

    var previewUrl = Content.Url.create("preview");
    if (previewUrl.isLeft()) {
      invalidValues.addInvalidValue(previewUrl.getLeft());
    }


    if (invalidValues.getInvalidValues().isEmpty()) {
      fileUploadService.uploadFile(params.file);
      fileUploadService.uploadFile(params.file);
      return repository.create(
              type.get(),
              name.get(),
              dateCreated,
              author,
              format.get(),
              url.get(),
              previewUrl.get());
    }

    return CompletableFuture.completedFuture(Either.left(invalidValues));
  }
}
