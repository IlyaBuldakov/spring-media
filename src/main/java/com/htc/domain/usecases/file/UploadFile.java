package com.htc.domain.usecases.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.file.File;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий добавления файла.
 */
@Component
@AllArgsConstructor
public final class UploadFile implements UseCase<File, File> {
  private final FileRepository repository;

  @Override
  public Future<Either<Failure, File>> execute(File file) {
    return repository.upload(file);
  }
}
