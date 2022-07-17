package com.htc.infrastructure.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.file.Format;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileBody;
import com.htc.domain.entities.utility.parameters.file.FileName;
import com.htc.domain.entities.utility.parameters.file.FileUrlPath;
import com.htc.domain.repositories.FileRepository;
import com.htc.infrastructure.models.file.FileModel;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория файла.
 */
@Repository
@AllArgsConstructor
public class FileRepositoryImplementation implements FileRepository {
  Files files;

  @Override
  public CompletableFuture<Either<Failure, File>> upload(FileName name,
                                                         DateCreated dateCreated,
                                                         Format format,
                                                         FileUrlPath fileUrlPath,
                                                         FileBody file) {
    var fileModel = new FileModel(
            0L,
            name.getValue(),
            dateCreated.getValue(),
            format.getName(),
            fileUrlPath.getValue(),
            file.getValue());
    return EitherHelper.goodRight(files.save(fileModel));
  }

  @Override
  public CompletableFuture<Either<Failure, File>> get(Id id) {
    return files.findById((long) id.getValue()).thenApplyAsync(Either::right);
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    files.deleteById(id.getValue());
    return EitherHelper.goodRight(null);
  }
}
