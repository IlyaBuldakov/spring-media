package com.htc.application.dto.file;

import com.htc.domain.entities.files.File;
import com.htc.domain.entities.tasks.Task;
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
  private final @Getter String file;
  /**
   * URL файла.
   *
   * @return URL файла.
   */
  private final @Getter Task task;

  /**
   * Создаёт экземпляр класса {@link FileRequestDto}.
   *
   * @param file Файл.
   */
  public FileRequestDto(File file) {
    this.file = file.getFile();
    this.task = file.getTask();
  }
}