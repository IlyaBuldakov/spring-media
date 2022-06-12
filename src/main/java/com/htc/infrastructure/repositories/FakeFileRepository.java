package com.htc.infrastructure.repositories;

import com.github.javafaker.Faker;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.files.File;
import com.htc.domain.entities.files.FileFormat;
import com.htc.domain.repositories.FileRepository;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.stereotype.Component;

/**
 * Реализация репозитория файлов с ненастоящими данными.
 */
@Component
public class FakeFileRepository implements FileRepository {
  private static final Faker faker = Faker.instance(new Locale("ru"));
  private static final LocalDateTime date = LocalDateTime.now();
  private static final FileFormat format = FileFormat.DOC;
  private static final String urlFile = "test/url/file";
  private static final List<File> files = List.of(
      File.upload(
          1,
          faker.file().fileName(),
          date,
          format,
          urlFile,
          1
      ).get()
  );

  @Override
  public Future<Either<Failure, File>> upload(File file) {
    return null;
  }

  @Override
  public Future<Either<Failure, Void>> delete(int id) {
    return null;
  }

  @Override
  public Future<Either<Failure, Iterable<File>>> getFilesByTaskId(int taskId) {
    var filesByTaskId = files.stream()
        .filter(u -> u.getTaskId() == taskId)
        .toList();
    return CompletableFuture.completedFuture(Either.right(filesByTaskId));
  }
}
