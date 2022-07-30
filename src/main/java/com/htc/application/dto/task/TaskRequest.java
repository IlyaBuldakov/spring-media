package com.htc.application.dto.task;

import com.htc.application.dto.BaseResponse;
import com.htc.domain.entities.content.ContentType;
import java.time.LocalDate;
import lombok.Getter;

/**
 * Представление задачи (запрос).
 */
public class TaskRequest implements BaseResponse {

  /**
   * Имя задачи.
   */
  private @Getter String name;

  /**
   * Тип задачи.
   */
  private @Getter ContentType type;

  /**
   * Описание задачи.
   */
  private @Getter String description;

  /**
   * Автор задачи.
   */
  private @Getter String author;

  /**
   * Исполнитель задачи.
   */
  private @Getter String executor;

  /**
   * Дата выполнения задачи (срок выполнения).
   */
  private @Getter LocalDate dateExpired;
}
