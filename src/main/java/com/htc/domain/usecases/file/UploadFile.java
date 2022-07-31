package com.htc.domain.usecases.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.file.Format;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.file.FileBody;
import com.htc.domain.entities.utility.parameters.file.FileName;
import com.htc.domain.entities.utility.parameters.file.FileUrlPath;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий добавления файла.
 */
@AllArgsConstructor
public final class UploadFile implements UseCase<UploadFile.Params, File> {
  /**
   * Параметры сценария добавления файла.
   *
   * @param name наименование
   * @param format формат
   * @param fileUrlPath путь до файла
   * @param file содержимое файла
   */
  public record Params(FileName name,
                       Format format,
                       FileUrlPath fileUrlPath,
                       FileBody file) {}

  private final FileRepository repository;

  @Override
  public CompletableFuture<Either<Failure, File>> execute(Params params) {
    var failure = new InvalidValues();
    var dateCreated = DateCreated.create();
    if (dateCreated.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_DATE_CREATED, "Date created");
    }
    return failure.getValues().size() == 0
            ? repository.upload(params.name(), dateCreated.get(), params.format(),
            params.fileUrlPath(), params.file())
            : EitherHelper.badLeft(failure);
  }
}
