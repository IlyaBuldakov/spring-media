package finalproject.utils.validators;

import finalproject.application.dto.content.InnerContentTransferObject;
import finalproject.domain.entities.content.ContentFormat;
import finalproject.domain.entities.content.ContentType;
import finalproject.domain.entities.failures.BadRequest;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.failures.InternalServerError;
import finalproject.domain.entities.failures.Messages;
import io.vavr.control.Either;
import java.io.InputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Вспомогательный класс для проверки файла контента.
 */
public class ValidateContent {
  private final Validators validators;

  public ValidateContent(Validators validators) {
    this.validators = validators;
  }

  /**
   * Непосредственно метод валидации файла контента.
   *
   * @param file файл контента
   * @return при успешной валидации возвращает объект с полученными параметрами
   */
  public Either<Failure, InnerContentTransferObject> validateContent(MultipartFile file) {
    if (!validators.validateNotNull(file.getOriginalFilename(), "file")) {
      return Either.left(new BadRequest(Messages.UNACCEPTABLE_FILE, validators.getProblems()));
    }
    String filename = StringUtils.cleanPath(file.getOriginalFilename());
    String extension = FilenameUtils.getExtension(file.getOriginalFilename());
    if (file.isEmpty() || filename.contains("..")) {
      validators.getProblems().add("file");
      return Either.left(new BadRequest(Messages.UNACCEPTABLE_FILE, validators.getProblems()));
    }

    if (!validators.validateNotNull(ContentFormat.valueOf(extension.toUpperCase()), "file")) {
      return Either.left(new BadRequest(
              Messages.UNACCEPTABLE_FILE_EXTENSION, validators.getProblems()));
    }
    ContentFormat contentFormat = ContentFormat.valueOf(extension.toUpperCase());
    try (InputStream is = file.getInputStream()) {
      Tika tika = new Tika();
      String mimeType = tika.detect(is);
      if (!(mimeType.contains("audio")
                      || mimeType.contains("video")
                      || mimeType.contains("image"))

      ) {
        validators.getProblems().add("file");
        return Either.left(
                new BadRequest(Messages.UNACCEPTABLE_FILE_CONTENT, validators.getProblems()));
      }
      ContentType type = switch (extension.toLowerCase()) {
        case "jpg", "png" -> ContentType.PHOTO;
        case "mp3", "m4a", "flac" -> ContentType.AUDIO;
        case "avi", "mp4" -> ContentType.VIDEO;
        default -> null;
      };
      return Either.right(new InnerContentTransferObject(filename, type, contentFormat, extension));
    } catch (Exception e) {
      return Either.left(new InternalServerError(Messages.INTERNAL_SERVER_ERROR));
    }

  }
}