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
import org.springframework.stereotype.Component;

/**
 * Сценарий добавления нового файла в задачу.
 */
@Component
@AllArgsConstructor
public final class UploadFile implements UseCase<UploadFile.Params, File> {
  /**
   * Параметры сценария добавления файла.
   */

  public record Params(String name, String nameKey,
                       Format format, String formatKey,
                       String fileUrlPath, String fileUrlPathKey,
                       String file, String fileKey) {}

  private final FileRepository repository;

  @Override
  public CompletableFuture<Either<Failure, File>> execute(Params params) {
    var failure = new InvalidValues();
    var name = FileName.create(params.name());
    if (name.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_NAME, params.nameKey);
    }
    var dateCreated = DateCreated.create();
    if (dateCreated.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_DATE_CREATED, "Date created");
    }
    var fileUrlPath = FileUrlPath.create(params.fileUrlPath());
    if (fileUrlPath.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_FILE_URL_PATH, params.fileUrlPathKey);
    }
    var file = FileBody.create(params.file());
    if (file.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_FILE_BODY, params.fileKey);
    }

    return failure.getValues().size() == 0
            ? repository.upload(
                    name.get(),
                    dateCreated.get(),
                    params.format(),
                    fileUrlPath.get(),
                    file.get()
              )
            : EitherHelper.badLeft(failure);
  }
}
