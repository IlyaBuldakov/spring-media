package com.htc.application.dto.content;

import com.htc.application.dto.file.FileDto;
import com.htc.application.dto.task.TaskResponse;
import com.htc.domain.entities.files.File;
import com.htc.domain.entities.tasks.Task;
import lombok.Getter;

/**
 * Представление запроса создания медиаконтента.
 */
public class ContentCreateRequestDto {
  /**
   * Файл медиаконтента.
   *
   * @return Файл медиаконтента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter FileDto file;
  /**
   * Задача связаная с создаваемым медиаконтента.
   *
   * @return Задача.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int task;

  /**
   * Создаёт экземпляр класса {@link ContentDto}.
   *
   * @param file Файл медиаконтента.
   * @param task Задача.
   */
  public ContentCreateRequestDto(File file, Task task) {
    this.file = new FileDto(file);
    this.task = task.getId().getValue();
  }
}
