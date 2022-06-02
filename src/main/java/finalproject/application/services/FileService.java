package finalproject.application.services;

import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.file.File;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.Future;

public interface FileService {
  Future<Either<Failure, Integer>> attachFileToTask (File file);
  Future<Either<Failure, Void>> deleteFileById (int id);
  Future<Either<Failure, File>> getFileById (int id);
  Future<Either<Failure, List<File>>> getAllFilesRelatedToTask (int taskId);

}
