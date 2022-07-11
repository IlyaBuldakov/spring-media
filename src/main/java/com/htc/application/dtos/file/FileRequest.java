package com.htc.application.dtos.file;

import com.htc.domain.entities.file.Format;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Запрос. Основное представление сущности файла.
 */
@AllArgsConstructor
public class FileRequest {
  /**
   * Имя.
   *
   * @return name имя
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String name;

  /**
   * Формат.
   *
   * @see Format
   *
   * @return format формат
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Format format;

  /**
   * Путь.
   *
   * @return fileUrlPath путь
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String fileUrlPath;


  /**
   * Содержимое в base64.
   *
   * @return file содержимое
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String file;

  private FileRequest() {}
}
