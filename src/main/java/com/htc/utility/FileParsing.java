package com.htc.utility;

import com.htc.domain.entities.File;
import com.htc.domain.usecases.file.UploadFile;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.zip.ZipInputStream;
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
   * @throws Exception Может вернуть исключение.
   */
  public static UploadFile.Params fileParsing(
      MultipartFile file,
      int taskId,
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

    // TODO Реализовать отличие файла doc от xml.
    // TODO Реализовать проверку существования параметра.
    File.Format fileFormat = switch (fileType) {
      case ("word/numbering.xml") -> File.Format.DOCX;
      case ("application/msword") -> File.Format.DOC;
      case ("xl/comments1.xml") -> File.Format.XLSX;
      case ("application/vnd.ms-exel") -> File.Format.XLS;
      case ("application/pdf") -> File.Format.PDF;
      case ("application/x-tika-msoffice") -> File.Format.DOC;
      default -> null;
    };

    String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
    java.io.File folder = new java.io.File(realPath);
    if (!folder.isDirectory()) {
      folder.mkdirs();
    }

    LocalDateTime dateCreated = LocalDateTime.now();
    // TODO Реализовать вывод ошибки, если не корретный файл.
    file.transferTo(new java.io.File(folder, newFileName));
    return new UploadFile.Params(
        newFileName, "name",
        dateCreated, "dateCreated",
        fileFormat, "format",
        realPath, "urlFile",
        taskId, "taskId");
  }
}
