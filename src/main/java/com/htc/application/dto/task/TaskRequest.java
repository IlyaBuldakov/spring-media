package com.htc.application.dto.task;

import com.htc.domain.entities.content.ContentType;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Представление задачи (запрос).
 */
public class TaskRequest {

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
