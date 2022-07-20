package com.htc.application.dtos.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Запрос. Основное представление сущности контента.
 */
@AllArgsConstructor
public class ContentRequest {
  /**
   * Идентификатор задачи.
   *
   * @see com.htc.domain.entities.task.Task
   *
   * @return task идентификатор задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Long taskId;

  /**
   * Идентификатор файла.
   *
   * @see com.htc.domain.entities.file.File
   *
   * @return file идентификатор файла
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Long fileId;

  private ContentRequest() {}
}
