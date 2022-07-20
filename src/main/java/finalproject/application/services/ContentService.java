package finalproject.application.services;

import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.file.File;
import io.vavr.control.Either;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;
import java.util.concurrent.Future;

public interface ContentService {
  Future<Either<Failure, String>> attachFileToTask (MultipartFile file, int taskId);
  Future<Either<Failure, Void>> deleteFileById (int id);

  Future<Either<Failure, List<File>>> getAllFilesRelatedToTask (int taskId);

}
