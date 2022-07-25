package finalproject.application.services.impl;



import finalproject.application.services.ContentService;
import finalproject.application.services.FileStorageService;
import finalproject.domain.entities.content.ContentFormat;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.filedocuments.FileDocuments;
import finalproject.infrastructure.repositories.ContentRepository;
import finalproject.infrastructure.repositories.TaskRepository;
import io.vavr.control.Either;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Реализация сервиса контента.
 */
@Service
@AllArgsConstructor
public class ContentServiceImpl implements ContentService {

  static final String contentPath = "./src/main/resources/static/content";
  static final String returnRelativePath = "/content/";
  FileStorageService fileStorageService;
  TaskRepository taskRepository;
  ContentRepository contentRepository;
  ArrayList<String> problems;



  @Override
  public CompletableFuture<Either<Failure, String>> attachFileToTask(MultipartFile file,
                                             int taskId) throws IOException {
    String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
    String filename = StringUtils.cleanPath(originalFilename);
    String extension = FilenameUtils.getExtension(originalFilename);
    Tika tika = new Tika();
    String mimeType = tika.detect(file.getInputStream());
    if (!Arrays.stream(ContentFormat.values()).map(Enum::toString)
            .toList().contains(extension.toUpperCase())
            || !mimeType.contains(extension.toLowerCase())) {
      problems.add("file");
      return CompletableFuture.completedFuture(Either.left(
              new Failure(Failure.Messages.UNACCEPTABLE_FILE_FORMAT, problems)));
    }

    Path path = Paths.get(contentPath, Integer.toString(taskId));
    if (fileStorageService.save(file, path, filename)) {
      return CompletableFuture.completedFuture(Either.right(
              returnRelativePath + taskId + "/" + filename));
    } else {
      return CompletableFuture.completedFuture(Either.left(
              new Failure(Failure.Messages.INTERNAL_SERVER_ERROR)));
    }
  }




  @Override
  public CompletableFuture<Either<Failure, Void>> deleteFileById(int id) {
    return null;
  }



  @Override
  public CompletableFuture<Either<Failure, List<FileDocuments>>>
      getAllFilesRelatedToTask(int taskId) {
    return null;
  }
}
