package com.htc.application.dto.file;

import com.htc.domain.entities.files.File;
import lombok.Getter;

/**
 * представление запроса файла.
 */
public class FileRequestDto {
  /**
   * Формат файла.
   *
   * @return Формат файла.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String file;
  /**
   * URL файла.
   *
   * @return URL файла.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int task;

  /**
   * Создаёт экземпляр класса {@link FileRequestDto}.
   *
   * @param file Файл.
   */
  public FileRequestDto(File file) {
    this.file = file.getFile();
    this.task = file.getTask().getId().getValue();
  }
}