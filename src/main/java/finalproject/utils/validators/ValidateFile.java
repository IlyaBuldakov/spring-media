package finalproject.utils.validators;

import finalproject.application.dto.content.InnerContentTransferObject;
import finalproject.application.dto.file.InnerFileTransferObject;
import finalproject.domain.entities.content.ContentFormat;
import finalproject.domain.entities.content.ContentType;
import finalproject.domain.entities.failures.BadRequest;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.failures.InternalServerError;
import finalproject.domain.entities.failures.Messages;
import finalproject.domain.entities.filedocuments.Format;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@AllArgsConstructor
public class ValidateFile {
  private final Validators validators;

  public Either<Failure, InnerFileTransferObject> validateFile(MultipartFile file) {
    if (!validators.validateNotNull(file.getOriginalFilename(), "file")) {
      return Either.left(new BadRequest(Messages.UNACCEPTABLE_FILE, validators.getProblems()));
    }
    String filename = StringUtils.cleanPath(file.getOriginalFilename());
    String extension = FilenameUtils.getExtension(file.getOriginalFilename());
    if (file.isEmpty() || filename.contains("..")) {
      validators.getProblems().add("file");
      return Either.left(new BadRequest(Messages.UNACCEPTABLE_FILE, validators.getProblems()));
    }

    if(!validators.validateNotNull(Format.valueOf(extension.toUpperCase()), "file")) {
      return Either.left(new BadRequest(Messages.UNACCEPTABLE_FILE_EXTENSION, validators.getProblems()));
    }
    Format fileFormat = Format.valueOf(extension.toUpperCase());
    try (InputStream is = file.getInputStream()) {
      Tika tika = new Tika();
      String mimeType = tika.detect(is);
      if (!(mimeType.equals(fileFormat.myme))
      ) {
        validators.getProblems().add("file");
        return Either.left(
                new BadRequest(Messages.UNACCEPTABLE_FILE_CONTENT, validators.getProblems()));
      }
      return Either.right(new InnerFileTransferObject(filename, fileFormat));
    } catch (Exception e) {
      return Either.left(new InternalServerError(Messages.INTERNAL_SERVER_ERROR));
    }

  }
}
