package com.htc.application.dto.content;

/**
 * Представление сущности контента (запрос).
 */
public class ContentRequest {
  /**
   * Файл контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  public String file;

  /**
   * Идентификатор задачи содержащей контент.
   */
  @SuppressWarnings("JavadocDeclaration")
  public int taskId;
}
