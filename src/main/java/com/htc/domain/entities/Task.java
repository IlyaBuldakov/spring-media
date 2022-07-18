package com.htc.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Задача.
 */
public interface Task {
  /**
   * Идентификатор задачи.
   *
   * @return id Идентификатор задачи.
   */
  Id getId();

  /**
   *  Название задачи.
   *
   * @return name Название задачи.
   */
  String getName();

  /**
   *  Тип контента.
   *
   * @return contentType Тип контента, см. {@Link Content.Type}
   */
  Content.Type getContentType();

  /**
   *  Описание задачи.
   *
   * @return description Описание задачи.
   */
  String getDescription();

  /**
   *  Приложенные к задаче файлы.
   *
   * @return file Приложенные к задаче файлы, см. {@Link File}
   */
  int[] getFileId();

  /**
   *  Идентификатор автора задачи.
   *
   * @return authorId Идентификатор автора задачи.
   */
  int getAuthorId();

  /**
   *  Идентификатор исполнителя задачи.
   *
   * @return executorId Идентификатор исполнителя задачи.
   */
  int executorId();

  /**
   *  Дата создания задачи.
   *
   * @return dateCreated Дата создания задачи.
   */
  LocalDateTime getDateCreated();

  /**
   *  Дата окончания задачи.
   *
   * @return dateExpired Дата окончания задачи.
   */
  LocalDateTime getDateExpired();

  /**
   *  Приложенный контент.
   *
   * @return contents Приложенный контент, см. {@Link Content}
   */
  int[] getContentsId();

  /**
   *  Комментарий в задаче.
   *
   * @return comments Комментарии в задаче, см. {@Link Comment}
   */
  int[] getCommentsId();

  /**
   *  Статус задачи.
   *
   * @return status Статус задачи, см. {@Link Status}
   */
  Status getStatus();

  /**
   * Статус задачи.
   */
  enum Status {

    /**
     * Одобрено.
     */
    @JsonProperty("approved")
    APPROVED(3, "Одобрено"),
    /**
     * Обратная связь.
     */
    @JsonProperty("feedback")
    FEEDBACK(2, "Обратная связь"),
    /**
     * В работе.
     */
    @JsonProperty("inWork")
    IN_WORK(1, "В работе");

    /**
     * Идентификатор статуса задачи.
     *
     * @return id Идентификатор статуса задачи.
     */
    private final @Getter int id;
    /**
     * Название статуса задачи.
     *
     * @return name Название статуса задачи.
     */
    private final @Getter String name;

    Status(int id, String name) {
      this.id = id;
      this.name = name;
    }
  }
}
