package com.htc.domain.entities.files;

import lombok.Getter;

/**
 * Форматы файлов.
 */
public enum FileFormat {

  DOC("DOC"),
  DOCX("DOCX"),
  XLS("XLS"),
  XLSX("XLSX"),
  PDF("PDF");

  /**
   * Формат файла.
   *
   * @return format Формат файла.
   */
  private final @Getter String format;

  FileFormat(String format) {
    this.format = format;
  }
}
