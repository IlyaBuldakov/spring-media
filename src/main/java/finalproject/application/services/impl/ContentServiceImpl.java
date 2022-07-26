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
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

/**
 * Реализация сервиса контента.
 */
@Service
@AllArgsConstructor
public class ContentServiceImpl implements ContentService {

  static final String staticPath = "./src/main/resources/static";
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
    String previewUrl = getPreviewUrl(extension, filename, path);

    return CompletableFuture.completedFuture(Either.right(fileUrl + " " + previewUrl));

  }

  public String getPreviewUrl(String extension, String filename, Path path) throws IOException {
    ContentType type = switch(extension.toLowerCase()) {
      case "jpg", "png" -> ContentType.PHOTO;
      case "mp3", "m4a", "flac" -> ContentType.AUDIO;
      case "avi", "mp4" -> ContentType.VIDEO;
      default -> null;
    };

    if (type == ContentType.PHOTO) {
      BufferedImage preview = ImageIO.read(new File(path.resolve(filename).toString()));
      Image resultingImage = preview.getScaledInstance(640, 480, Image.SCALE_DEFAULT);
      BufferedImage outputImage = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
      outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
      String previewPath = path.resolve("preview_"
              + filename.substring(0, filename.lastIndexOf(".")) + ".jpg").toString();
      File output = new File(previewPath);
      ImageIO.write(outputImage, "jpg", output);
      return "/" + Paths.get(staticPath).toUri().relativize(Paths.get(previewPath).toUri());
    }

    if (type == ContentType.VIDEO) {
      String previewPath = path.resolve("preview_"
              + filename.substring(0, filename.lastIndexOf(".")) + ".jpg").toString();
      File output = new File(previewPath);
      FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(path.resolve(filename).toString());
      grabber.start();
      Frame frame = grabber.grabImage();
      Java2DFrameConverter converter = new Java2DFrameConverter();
      BufferedImage bi = converter.convert(frame);
      Image resultingImage = bi.getScaledInstance(640, 480, Image.SCALE_DEFAULT);
      BufferedImage outputImage = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
      outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
      ImageIO.write(outputImage, "jpg", output);
      return "/" + Paths.get(staticPath).toUri().relativize(Paths.get(previewPath).toUri());
    }
return null;
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
