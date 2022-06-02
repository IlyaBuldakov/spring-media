package finalproject.application.dto.file;

import java.time.LocalDateTime;
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
    DOC,
    DOCX,
    XLS,
    XLSX,
    PDF,
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



}
