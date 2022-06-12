package com.htc.domain.usecases.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.files.File;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения файлов по идентификатору задачи.
 */
@Component
@AllArgsConstructor
public class GetFilesByTaskId implements UseCase<Integer, Iterable<File>> {
  private final FileRepository repository;

  @Override
  public Future<Either<Failure, Iterable<File>>> execute(Integer taskId) {
    return repository.getFilesByTaskId(taskId);
  }
}