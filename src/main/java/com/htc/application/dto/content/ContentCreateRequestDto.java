package com.htc.application.dto.content;

import com.htc.domain.entities.attributes.Id;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

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
  private final @Getter MultipartFile file;
  /**
   * Задача связаная с создаваемым медиаконтента.
   *
   * @return Задача.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Id taskId;

  /**
   * Создаёт экземпляр класса {@link ContentDto}.
   *
   * @param file Файл медиаконтента.
   * @param taskId Задача.
   */
  public ContentCreateRequestDto(MultipartFile file, Id taskId) {
    this.file = file;
    this.taskId = taskId;
  }
}
