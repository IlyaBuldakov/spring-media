package finalproject.application.services.impl;



import finalproject.application.dto.content.InnerContentTransferObject;
import finalproject.application.dto.failures.BadRequestDto;
import finalproject.application.services.ContentService;
import finalproject.application.services.FileStorageService;
import finalproject.domain.entities.content.Content;
import finalproject.domain.entities.content.ContentType;
import finalproject.domain.entities.failures.*;
import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.task.TaskStatus;
import finalproject.domain.entities.user.Role;
import finalproject.domain.entities.user.User;
import finalproject.infrastructure.repositories.ContentRepository;
import finalproject.infrastructure.repositories.TaskRepository;
import finalproject.infrastructure.repositories.UserRepository;
import finalproject.utils.validators.ValidateContent;
import finalproject.utils.validators.Validators;
import io.vavr.control.Either;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.imageio.ImageIO;
import lombok.AllArgsConstructor;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Реализация сервиса контента.
 */
@Service
@AllArgsConstructor
public class ContentServiceImpl implements ContentService {

  static final String staticPath = "./src/main/resources/static";
  static final String contentPath = "./src/main/resources/static/content";
  static final String returnRelativePath = "/content/";
  static final Path defaultPath = Path.of(staticPath);

  FileStorageService fileStorageService;
  TaskRepository taskRepository;

  UserRepository userRepository;
  ContentRepository contentRepository;
  ArrayList<String> problems;


  @Async
  @Override
  public CompletableFuture<Either<Failure, Content>> attachFileToTask(MultipartFile file,
                                             int taskId, int auth) throws IOException {
    User authorizedUser = userRepository.findById(auth).get();
    if (!taskRepository.existsById(taskId)) {
      return CompletableFuture.completedFuture(
              Either.left(new NotFound(Messages.TASK_NOT_FOUND)));
    }

    Task task = taskRepository.findById(taskId).get();
    if (authorizedUser.getRole() != Role.ADMIN && !task.getContentMaker().equals(authorizedUser)) {
      return CompletableFuture.completedFuture(
              Either.left(new NotAuthorized(Messages.NOT_ENOUGH_AUTHORITY)));
    }
    ValidateContent validateContent = new ValidateContent(new Validators());
    InnerContentTransferObject fileData = validateContent.validateContent(file).getOrElseThrow(BadRequestDto::new);
    if (!task.getType().equals(fileData.getType())) {
      problems.add("file");
      return CompletableFuture.completedFuture(
              Either.left(new BadRequest(Messages.UNACCEPTABLE_TASK_CONTENT, problems)));
    }

    Path path = Paths.get(contentPath, Integer.toString(taskId));
    if (!fileStorageService.save(file, path, fileData.getFilename())) {
      return CompletableFuture.completedFuture(Either.left(
              new InternalServerError(Messages.INTERNAL_SERVER_ERROR)));
    }
    String fileUrl = returnRelativePath + taskId + "/" + fileData.getFilename();
    String previewUrl = getPreviewUrl(fileData.getFilename(), path, fileData.getType());
    Content content = new Content(fileData.getType(), fileData.getFilename(), fileData.getFormat(),
            previewUrl, task, fileUrl, false);
    content.setDateCreated(LocalDateTime.now());
    contentRepository.save(content);
    task.setTaskStatus(TaskStatus.FEEDBACK);
    taskRepository.save(task);
    return CompletableFuture.completedFuture(Either.right(content));

  }

  @Override
  public CompletableFuture<Either<Failure, Void>> deleteContentById(int id) {
    List<String> problems = new ArrayList<>();
    if (taskRepository.existsById(id)) {
      taskRepository.deleteById(id);
      return CompletableFuture.completedFuture(Either.right(null));
    }
    if (id <= 1) {
      problems.add("id");
      return CompletableFuture.completedFuture(Either.left(
              new BadRequest(Messages.INVALID_VALUES, problems)));
    }
    return CompletableFuture.completedFuture(Either.left(
            new NotFound(Messages.CONTENT_NOT_FOUND)));
  }

  @Override
  public CompletableFuture<Either<Failure, List<Content>>> getAllContent() {
    return CompletableFuture.completedFuture(Either.right(contentRepository.findByIsPublished(true)));
  }

  /**
   * Метод, получающий preview контента из полученных файлов и возвращающий его URI.
   *
   * @param filename имя файла
   * @param path путь к каталогу размещения файла
   * @param type тип контента
   * @return URI для изображения-preview
   * @throws IOException для операции сохранения файла
   */
  public String getPreviewUrl(String filename, Path path, ContentType type) throws IOException {

    File toConvert = new File(path.resolve(filename).toString());
    BufferedImage preview;
    if (type == ContentType.PHOTO) {
      preview = ImageIO.read(toConvert);
    } else
      if (type == ContentType.VIDEO) {
        try(FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(toConvert)) {
        grabber.start();
        Frame frame = grabber.grabImage();
        Java2DFrameConverter converter = new Java2DFrameConverter();
        preview = converter.convert(frame);}
      } else {
        return  "/" + defaultPath.toUri().relativize(Paths.get(staticPath, "defaultmusicicon.png")
              .toUri());
      }
    Image resultingImage = preview.getScaledInstance(640, 480, Image.SCALE_DEFAULT);
    BufferedImage outputImage = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
    String previewPath = path.resolve("preview_"
              + filename.substring(0, filename.lastIndexOf(".")) + ".jpg").toString();
    File output = new File(previewPath);
    ImageIO.write(outputImage, "jpg", output);
    return "/" + defaultPath.toUri().relativize(Paths.get(previewPath).toUri());
  }








}
