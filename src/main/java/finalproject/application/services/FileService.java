package finalproject.application.services;

import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.filedocuments.FileDocument;
import io.vavr.control.Either;
import org.springframework.web.multipart.MultipartFile;
import java.util.concurrent.CompletableFuture;

public interface FileService {
  CompletableFuture<Either<Failure, FileDocument>> uploadFileToTask(
          MultipartFile file, int taskId, int authorizedUserId);

  CompletableFuture<Either<Failure, Void>> deleteFileById(int id, int authorizedUserId);
}
