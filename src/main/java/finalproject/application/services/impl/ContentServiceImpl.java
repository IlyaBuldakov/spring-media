package finalproject.application.services.impl;



import finalproject.application.dto.content.InnerContentTransferObject;
import finalproject.application.dto.failures.BadRequestDto;
import finalproject.application.services.ContentService;
import finalproject.application.services.FileStorageService;
import finalproject.domain.entities.content.Content;
import finalproject.domain.entities.content.ContentType;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.filedocuments.FileDocuments;
import finalproject.domain.entities.task.Task;
import finalproject.infrastructure.repositories.ContentRepository;
import finalproject.infrastructure.repositories.TaskRepository;
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
  ContentRepository contentRepository;
  ArrayList<String> problems;


  @Async
  @Override
  public CompletableFuture<Either<Failure, Content>> attachFileToTask(MultipartFile file,
                                             int taskId) throws IOException {
    if (!taskRepository.existsById(taskId)) {
      return CompletableFuture.completedFuture(
              Either.left(new Failure(Failure.Messages.ENTITY_NOT_FOUND)));
    }
    Task task = taskRepository.findById(taskId).get();
    ValidateContent validateContent = new ValidateContent(new Validators());
    InnerContentTransferObject fileData = validateContent.validateContent(file).getOrElseThrow(BadRequestDto::new);
    if (!task.getType().equals(fileData.getType())) {
      problems.add("file");
      return CompletableFuture.completedFuture(
              Either.left(new Failure(Failure.Messages.UNACCEPTABLE_TASK_CONTENT, problems)));
    }

    Path path = Paths.get(contentPath, Integer.toString(taskId));
    if (!fileStorageService.save(file, path, fileData.getFilename())) {
      return CompletableFuture.completedFuture(Either.left(
              new Failure(Failure.Messages.INTERNAL_SERVER_ERROR)));
    }
    String fileUrl = returnRelativePath + taskId + "/" + fileData.getFilename();
    String previewUrl = getPreviewUrl(fileData.getFilename(), path, fileData.getType());
    Content content = new Content(fileData.getType(), fileData.getFilename(), fileData.getFormat(),
            previewUrl, task, fileUrl, false);
    content.setDateCreated(LocalDateTime.now());
    contentRepository.save(content);
    return CompletableFuture.completedFuture(Either.right(content));

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
