package finalproject.application.dto.file;

import java.time.LocalDateTime;

import finalproject.domain.entities.filedocuments.FileDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Файлы документов.
 */
@AllArgsConstructor
public class FileDto {

  /**
   * Форматы файлов документов.
   */
  public enum Format {
    doc,
    docx,
    xls,
    xlsx,
    pdf,
  }

  /**
   * Возвращает @return id.
   */
  private @Getter int id;

  /**
   * Возвращает @return String name название документа.
   */
  private @Getter String name;

  /**
   * Возвращает @return LocalDateTime dateCreated дату создания документа.
   */
  private @Getter LocalDateTime dateCreated;

  /**
   * Возвращает @return Format формат документа.
   */
  private @Getter Format format;

  /**
   * Возвращает @return строку пути к файлу.
   */
  private @Getter String url;

  public FileDto (FileDocument document) {
    this.id = document.getId();
    this.name = document.getName();
    this.dateCreated = document.getDateCreated();
    this.url = document.getUrl();
    this.format = Format.valueOf(document.getFormat().name().toLowerCase());
  }


}
