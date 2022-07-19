package finalproject.application.services.impl;

import finalproject.application.services.FileService;
import finalproject.application.services.FileStorageService;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.file.File;
import finalproject.domain.repositories.FileRepository;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.Future;

@AllArgsConstructor
public class FileServiceImpl implements FileService {

  FileStorageService fileStorageService;
  FileRepository fileRepository;

  @Override
  public Future<Either<Failure, Integer>> attachFileToTask(File file) {
    return null;
  }

  @Override
  public Future<Either<Failure, Void>> deleteFileById(int id) {
    return null;
  }

  @Override
  public Future<Either<Failure, File>> getFileById(int id) {
    return null;
  }

  @Override
  public Future<Either<Failure, List<File>>> getAllFilesRelatedToTask(int taskId) {
    return null;
  }
}
