package com.htc.domain.entities.file;

import lombok.Getter;

/**
 * Формат файла.
 */
public enum Format {
  DOC(1, "doc"),
  DOCX(2, "docx"),
  XLS(3, "xls"),
  XLSX(4, "xlsx"),
  PDF(5, "pdf"),
  JPG(6, "jpg"),
  PNG(7, "png"),
  MP3(8, "mp3"),
  M4A(9, "m4a"),
  FLAC(10, "flac"),
  AVI(11, "avi"),
  mp4(12, "mp4");

  /**
   * Идентификатор формата.
   *
   * @return id идентификатор формата
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;

  /**
   * Наименование формата.
   *
   * @return name наименование формата
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  Format(int id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * Получение списка расширений файла для REGEX выражения в виде строки.
   *
   * @return result список расширений
   */
  public static String getRegexFileFormats() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < Format.values().length; i++) {
      sb.append(Format.values()[i].getName()).append("|");
    }
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }

  /**
   * Получение формата по названию формата.
   *
   * @param name название формата
   * @return format {@link Format формат}
   */
  public static Format getFromName(String name) {
    for (Format format : Format.values()) {
      if (format.getName().equals(name))  {
        return format;
      }
    }
    return null;
  }
}
