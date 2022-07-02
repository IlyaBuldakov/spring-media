package com.htc.application.dto.content;

import com.htc.application.dto.file.FileDto;
import com.htc.application.dto.task.TaskDto;
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
  private final @Getter FileDto file;
  /**
   * Задача связаная с создаваемым медиаконтента.
   *
   * @return Задача.
   */
  private final @Getter TaskDto task;

  /**
   * Создаёт экземпляр класса {@link ContentDto}.
   *
   * @param file Файл медиаконтента.
   * @param task Задача.
   */
  public ContentCreateRequestDto(File file, Task task) {
    this.file = new FileDto(file);
    this.task = new TaskDto(task);
  }
}
