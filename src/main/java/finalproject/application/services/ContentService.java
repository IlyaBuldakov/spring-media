package finalproject.application.services;

import finalproject.domain.entities.content.Content;
import finalproject.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.multipart.MultipartFile;

/**
 * Сервис контента.
 */
public interface ContentService {
  CompletableFuture<Either<Failure, Content>> uploadContentToTask(
          MultipartFile file, int taskId, int authorizedUserId) throws IOException;

  CompletableFuture<Either<Failure, Void>> deleteContentById(int id, int auth);

  CompletableFuture<Either<Failure, List<Content>>> getAllContent();


}
