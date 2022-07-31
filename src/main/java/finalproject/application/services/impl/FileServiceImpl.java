package finalproject.application.services.impl;


import finalproject.application.dto.failures.BadRequestDto;
import finalproject.application.dto.file.InnerFileTransferObject;
import finalproject.application.services.FileService;
import finalproject.application.services.FileStorageService;
import finalproject.domain.entities.failures.BadRequest;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.failures.InternalServerError;
import finalproject.domain.entities.failures.Messages;
import finalproject.domain.entities.failures.NotAuthorized;
import finalproject.domain.entities.failures.NotFound;
import finalproject.domain.entities.filedocuments.FileDocument;
import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.user.Role;
import finalproject.domain.entities.user.User;
import finalproject.infrastructure.repositories.FileDocumentRepository;
import finalproject.infrastructure.repositories.TaskRepository;
import finalproject.infrastructure.repositories.UserRepository;
import finalproject.utils.validators.ValidateFile;
import finalproject.utils.validators.Validators;
import io.vavr.control.Either;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Имплементация сервиса для работы с файлами.
 */
@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

  UserRepository userRepository;
  TaskRepository taskRepository;

  FileDocumentRepository fileDocumentRepository;
  FileStorageService fileStorageService;


  static final String filesPath = "./src/main/resources/static/files";
  static final String returnRelativePath = "/files/";



  @Override
  public CompletableFuture<Either<Failure, FileDocument>> uploadFileToTask(
          MultipartFile file, int taskId, int authorizedUserId) {
    User authorizedUser = userRepository.findById(authorizedUserId).get();
    if (!taskRepository.existsById(taskId)) {
      return CompletableFuture.completedFuture(
              Either.left(new NotFound(Messages.TASK_NOT_FOUND)));
    }
    Task task = taskRepository.findById(taskId).get();
    if (authorizedUser.getRole() != Role.ADMIN && !task.getAuthor().equals(authorizedUser)) {
      return CompletableFuture.completedFuture(
              Either.left(new NotAuthorized(Messages.NOT_ENOUGH_AUTHORITY)));
    }
    ValidateFile validateFile = new ValidateFile(new Validators());
    InnerFileTransferObject fileData = validateFile
            .validateFile(file).getOrElseThrow(BadRequestDto::new);
    Path path = Paths.get(filesPath, Integer.toString(taskId));
    if (!fileStorageService.save(file, path, fileData.getFilename())) {
      return CompletableFuture.completedFuture(Either.left(
              new InternalServerError(Messages.INTERNAL_SERVER_ERROR)));
    }
    String fileUrl = returnRelativePath + taskId + "/" + fileData.getFilename();
    if (fileDocumentRepository.findOneByUrl(fileUrl).isPresent()) {
      FileDocument document = fileDocumentRepository.findOneByUrl(fileUrl).get();
      document.setDateCreated(LocalDateTime.now());
      return CompletableFuture.completedFuture(Either.right(document));
    } else {
      FileDocument document = new FileDocument(fileData.getFilename(),
            fileData.getFormat(), fileUrl, task);
      document.setDateCreated(LocalDateTime.now());
      fileDocumentRepository.save(document);
      return CompletableFuture.completedFuture(Either.right(document));
    }
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> deleteFileById(int id, int authorizedUserId) {
    User authorizedUser = userRepository.findById(authorizedUserId).get();
    List<String> problems = new ArrayList<>();
    if (id < 1) {
      problems.add("id");
      return CompletableFuture.completedFuture(Either.left(
              new BadRequest(Messages.INVALID_VALUES, problems)));
    }
    if (!fileDocumentRepository.existsById(id)) {
      return CompletableFuture.completedFuture(Either.left(
              new NotFound(Messages.FILE_NOT_FOUND)));
    }
    Task task = fileDocumentRepository.findById(id).get().getTask();
    if (authorizedUser.getRole() != Role.ADMIN && !task.getAuthor().equals(authorizedUser)) {
      return CompletableFuture.completedFuture(
              Either.left(new NotAuthorized(Messages.NOT_ENOUGH_AUTHORITY)));
    }
    task.getAllTaskFiles().remove(fileDocumentRepository.findById(id).get());
    if (fileDocumentRepository.findById(id).isPresent()) {
      fileDocumentRepository.deleteById(id);
    }
    return CompletableFuture.completedFuture(Either.right(null));
  }
}
