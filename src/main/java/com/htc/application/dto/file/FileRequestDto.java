package com.htc.application.dto.file;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

/**
 * представление запроса файла.
 */
public class  FileRequestDto {
  /**
   * Файл.
   *
   * @return Файл.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter MultipartFile file;
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
   * @param task Индентфикатор задачи.
   */
  public FileRequestDto(MultipartFile file, int task) {
    this.file = file;
    this.task = task;
  }
}