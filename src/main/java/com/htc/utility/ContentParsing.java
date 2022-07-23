package com.htc.utility;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Content.Format;
import com.htc.domain.usecases.content.UploadContent;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.zip.ZipInputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.tika.Tika;
import org.apache.tika.io.TemporaryResources;
import org.apache.tika.io.TikaInputStream;
import org.springframework.web.multipart.MultipartFile;

/**
 * Вспомогательный класс для получения параметров из загружаемого контента.
 */
public class ContentParsing {
  /**
   * Анализирует параметры файла.
   *
   * @param file Загруженный файл.
   * @param taskId Идентификатор задачи содержащей файл.
   * @param request Тело файла.
   * @return Params Параметры для создания сущности файл.
   * @throws Exception Может вернуть исключение.
   */
  public static UploadContent.Params fileParsing(
      MultipartFile file,
      int taskId,
      int authorId,
      HttpServletRequest request) throws Exception {

    String originalFileName = file.getOriginalFilename();
    assert originalFileName != null;
    String newFileName =
        UUID.randomUUID()
            + originalFileName.substring(originalFileName.lastIndexOf("."));

    Tika tika = new Tika();
    TikaInputStream tikaInputStream =
        TikaInputStream.get(file.getInputStream(),
            new TemporaryResources());
    String fileType = tika.detect(tikaInputStream);

    if (fileType.equals("application/zip")) {
      fileType = new ZipInputStream(file.getInputStream()).getNextEntry().getName();
    }

    // TODO Реализовать проверку существования параметра.
    Format format = switch (fileType) {
      case ("image/jpeg") -> Format.JPG;
      case ("image/png") -> Format.PNG;
      case ("audio/mpeg") -> Format.MP3;
      case ("video/mp4") -> Format.M4A;
      case ("audio/fla") -> Format.FLAC;
      case ("video/x-msvideo") -> Format.AVI;
      case ("video/quicktime") -> Format.MP4;
      default -> null;
    };

    Content.Type type = switch (format) {
      case JPG, PNG -> Content.Type.PHOTO;
      case MP3, M4A, FLAC -> Content.Type.AUDIO;
      case AVI, MP4 -> Content.Type.VIDEO;
    };

    String urlFile = request
        .getSession()
        .getServletContext()
        .getRealPath("/uploads/contents/" + type);

    java.io.File folder = new java.io.File(urlFile);
    if (!folder.isDirectory()) {
      folder.mkdirs();
    }

    LocalDateTime dateCreated = LocalDateTime.now();
    // TODO Реализовать вывод ошибки, если не корретный файл.
    // TODO Реализовать передачу пути превью.
    file.transferTo(new java.io.File(folder, newFileName));
    return new UploadContent.Params(
        type, "type",
        newFileName, "name",
        dateCreated, "dateCreated",
        authorId, "authorId",
        format, "format",
        urlFile, "urlFile",
        "string", "urlPreview",
        taskId, "taskId");
  }
}
