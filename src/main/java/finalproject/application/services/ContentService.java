package finalproject.application.services;

import finalproject.domain.entities.content.Content;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.filedocuments.FileDocuments;
import io.vavr.control.Either;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.multipart.MultipartFile;

/**
 * Сервис контента.
 */
public interface ContentService {
  CompletableFuture<Either<Failure, Content>> attachFileToTask(
          MultipartFile file, int taskId) throws IOException;

  CompletableFuture<Either<Failure, Void>> deleteContentById(int id);

  CompletableFuture<Either<Failure, List<Content>>> getAllContent();


}
