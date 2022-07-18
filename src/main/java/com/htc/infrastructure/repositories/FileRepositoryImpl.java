package com.htc.infrastructure.repositories;

import com.htc.domain.entities.File;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.FileRepository;
import com.htc.infrastructure.models.FileModel;
import com.htc.utility.Results;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория файлов.
 */
@Repository
public class FileRepositoryImpl implements FileRepository {
  @Autowired
  Files files;

  @Override
  public CompletableFuture<Either<Failure, File>> upload(
      String name,
      LocalDateTime dateCreated,
      File.Format format,
      String urlFile,
      int taskId) {
    var file = new FileModel(name, dateCreated, format, urlFile, taskId);
    return Results.succeed(files.save(file));
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    files.deleteById(id.getValue());
    return Results.succeed(null);
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<File>>> getFilesByTaskId(int taskId) {
    return null;
  }

  /**
   * ORM для доступа к данным файлов в СУБД.
   */
  public interface Files extends JpaRepository<FileModel, Integer> {

  }
}