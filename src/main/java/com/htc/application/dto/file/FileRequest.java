package com.htc.application.dto.file;

/**
 * Представление сущности файла (запрос).
 */
public class FileRequest {

  /**
   * Файл.
   */
  @SuppressWarnings("JavadocDeclaration")
  public String file;

  /**
   * Идентификатор задачи содержащей файл.
   */
  @SuppressWarnings("JavadocDeclaration")
  public String taskId;
}
