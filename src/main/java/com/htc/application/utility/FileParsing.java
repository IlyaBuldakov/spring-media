package com.htc.application.utility;

import static com.htc.domain.entities.File.Format.DOC;
import static com.htc.domain.entities.File.Format.DOCX;
import static com.htc.domain.entities.File.Format.PDF;
import static com.htc.domain.entities.File.Format.XLS;
import static com.htc.domain.entities.File.Format.XLSX;

import com.htc.domain.entities.File;
import com.htc.domain.usecases.file.UploadFile;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.tika.Tika;
import org.apache.tika.io.TemporaryResources;
import org.apache.tika.io.TikaInputStream;
import org.springframework.web.multipart.MultipartFile;

/**
 * Вспомогательный класс для получения параметров из загружаемого файла.
 */
public class FileParsing {
  /**
   * Анализирует параметры файла.
   *
   * @param file Загруженный файл.
   * @param taskId Идентификатор задачи содержащей файл.
   * @param request Тело файла.
   * @return Params Параметры для создания сущности файл.
   */
  @SuppressWarnings("checkstyle:NeedBraces")
  public static UploadFile.Params fileParsing(
      MultipartFile file,
      int taskId,
      HttpServletRequest request) throws Exception {
    String newFileName = null;
    String fileType = null;
    String format = null;
    File.Format fileFormat = null;
    String originalFileName = file.getOriginalFilename();

    if (originalFileName != null) {
      newFileName =
          UUID.randomUUID()
              + originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    if (newFileName != null) {
      Tika tika = new Tika();
      TikaInputStream tikaInputStream =
          TikaInputStream.get(file.getInputStream(),
              new TemporaryResources());
      fileType = tika.detect(tikaInputStream);
    }

    if (fileType != null) {
      format = switch (fileType) {
        case  ("application/zip"),
              ("text/plain"),
              ("application/x-tika-msoffice") -> file.getContentType();
        default -> null;
      };
    }

    if (format != null) {
      fileFormat = switch (format) {
        case ("application/vnd.openxmlformats-officedocument.wordprocessingml.document") -> DOCX;
        case ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") -> XLSX;
        case ("application/msword") -> DOC;
        case ("application/vnd.ms-excel") -> XLS;
        case ("application/pdf") -> PDF;
        default -> null;
      };
    }

    if (fileFormat != null) {
      String urlFile =
          request
          .getSession()
          .getServletContext()
          .getRealPath("/uploads/files/" + fileFormat);
      java.io.File folder = new java.io.File(urlFile);
      if (!folder.isDirectory()) {
        folder.mkdirs();
      }

      LocalDateTime dateCreated = LocalDateTime.now();
      file.transferTo(new java.io.File(folder, newFileName));

      java.io.File tempFile = new java.io.File(urlFile + "/" + newFileName);
      if (tempFile.isFile()) {
        return new UploadFile.Params(
            newFileName, "name",
            dateCreated, "dateCreated",
            fileFormat, "format",
            urlFile, "urlFile",
            taskId, "taskId");
      }
    }
    // TODO Реализовать возвращение ошибки.
    return null;
  }
}
