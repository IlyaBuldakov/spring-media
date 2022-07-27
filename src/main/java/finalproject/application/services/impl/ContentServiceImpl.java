package finalproject.application.services.impl;



import finalproject.application.services.ContentService;
import finalproject.application.services.FileStorageService;
import finalproject.domain.entities.content.ContentFormat;
import finalproject.domain.entities.content.ContentType;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.filedocuments.FileDocuments;
import finalproject.infrastructure.repositories.ContentRepository;
import finalproject.infrastructure.repositories.TaskRepository;
import io.vavr.control.Either;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import javax.imageio.ImageIO;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
  public CompletableFuture<Either<Failure, String>> attachFileToTask(MultipartFile file,
                                             int taskId) throws IOException {
    if (!taskRepository.existsById(taskId)) {
      return CompletableFuture.completedFuture(
              Either.left(new Failure(Failure.Messages.ENTITY_NOT_FOUND)));
    }
    String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
    String filename = StringUtils.cleanPath(originalFilename);
    String extension = FilenameUtils.getExtension(originalFilename);
    Tika tika = new Tika();
    String mimeType = tika.detect(file.getInputStream());
    ContentFormat contentFormat = ContentFormat.valueOf(extension.toUpperCase());

    if (!Arrays.stream(ContentFormat.values())
            .toList()
            .contains(contentFormat)
            ||
                    !(mimeType.contains("audio")
                    || mimeType.contains("video")
                    || mimeType.contains("image"))

    ) {
      problems.add("file");
      return CompletableFuture.completedFuture(Either.left(
              new Failure(Failure.Messages.UNACCEPTABLE_FILE_FORMAT, problems)));
    }

    Path path = Paths.get(contentPath, Integer.toString(taskId));
    if (!fileStorageService.save(file, path, filename)) {
      return CompletableFuture.completedFuture(Either.left(
              new Failure(Failure.Messages.INTERNAL_SERVER_ERROR)));
    }
    String fileUrl = returnRelativePath + taskId + "/" + filename;
    ContentType type = switch (extension.toLowerCase()) {
      case "jpg", "png" -> ContentType.PHOTO;
      case "mp3", "m4a", "flac" -> ContentType.AUDIO;
      case "avi", "mp4" -> ContentType.VIDEO;
      default -> null;
    };
    String previewUrl = getPreviewUrl(filename, path, type);
    String name = filename.substring(0, filename.lastIndexOf("."));



    return CompletableFuture.completedFuture(Either.right(fileUrl + " " + previewUrl));

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
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(toConvert);
        grabber.start();
        Frame frame = grabber.grabImage();
        Java2DFrameConverter converter = new Java2DFrameConverter();
        preview = converter.convert(frame);
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
