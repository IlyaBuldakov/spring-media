package com.htc.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Файл.
 */
public interface File {

  /**
   * Получает идентификатор файла.
   *
   * @return id Идентификатор файла.
   */
  Id getId();

  /**
   * Получает название файла.
   *
   * @return name Название файла.
   */
  String getName();

  /**
   * Получает дату создания файла.
   *
   * @return dateCreated Дата создания файла.
   */
  LocalDateTime getDateCreated();

  /**
   * Получает формат файла.
   *
   * @return format Формат файла, см. {@Link Format}
   */
  Format getFormat();

  /**
   * Получает путь к файлу.
   *
   * @return urlFile Путь к файлу.
   */
  String getUrlFile();

  /**
   * Получает идентификатор задачи, к которой прикреплен файл.
   *
   * @return taskId Идентификатор задачи, к которой прикреплен файл.
   */
  int getTaskId();

  /**
   * Формат файла.
   */
  enum Format {
    /**
     * DOC.
     */
    @JsonProperty("doc")
    DOC("DOC"),
    /**
     * DOCX.
     */
    @JsonProperty("docx")
    DOCX("DOCX"),
    /**
     * XLS.
     */
    @JsonProperty("xls")
    XLS("XLS"),
    /**
     * XLSX.
     */
    @JsonProperty("xlsx")
    XLSX("XLSX"),
    /**
     * PDF.
     */
    @JsonProperty("pdf")
    PDF("PDF");

    /**
     * Формат файла.
     *
     * @return format Формат файла.
     */
    @SuppressWarnings("JavadocDeclaration")
    private final @Getter
    String format;

    Format(String format) {
      this.format = format;
    }
  }
}
