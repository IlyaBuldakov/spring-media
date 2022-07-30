package finalproject.application.services.impl;


import finalproject.application.dto.failures.BadRequestDto;
import finalproject.application.dto.file.InnerFileTransferObject;
import finalproject.application.services.FileService;
import finalproject.application.services.FileStorageService;
import finalproject.domain.entities.failures.*;
import finalproject.domain.entities.filedocuments.FileDocument;
import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.user.Role;
import finalproject.domain.entities.user.User;
import finalproject.infrastructure.repositories.FileDocumentsRepository;
import finalproject.infrastructure.repositories.TaskRepository;
import finalproject.infrastructure.repositories.UserRepository;
import finalproject.utils.validators.ValidateFile;
import finalproject.utils.validators.Validators;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

  UserRepository userRepository;
  TaskRepository taskRepository;

  FileDocumentsRepository fileDocumentsRepository;
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
    InnerFileTransferObject fileData = validateFile.validateFile(file).getOrElseThrow(BadRequestDto::new);
    Path path = Paths.get(filesPath, Integer.toString(taskId));
    if (!fileStorageService.save(file, path, fileData.getFilename())) {
      return CompletableFuture.completedFuture(Either.left(
              new InternalServerError(Messages.INTERNAL_SERVER_ERROR)));
    }
    String fileUrl = returnRelativePath + taskId + "/" + fileData.getFilename();
    FileDocument document = new FileDocument(fileData.getFilename(), fileData.getFormat(), fileUrl, task);
    document.setDateCreated(LocalDateTime.now());
    fileDocumentsRepository.save(document);
    return CompletableFuture.completedFuture(Either.right(document));
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> deleteFileById(int id) {
    return null;
  }
}
