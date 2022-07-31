package finalproject.application.services;

import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.filedocuments.FileDocument;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.multipart.MultipartFile;

/**
 * Сервис для работы с файлами.
 */
public interface FileService {
  CompletableFuture<Either<Failure, FileDocument>> uploadFileToTask(
          MultipartFile file, int taskId, int authorizedUserId);

  CompletableFuture<Either<Failure, Void>> deleteFileById(int id, int authorizedUserId);
}
