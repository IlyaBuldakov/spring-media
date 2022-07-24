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
   * Получает идентификатор задачи.
   *
   * @return id Идентификатор задачи.
   */
  Id getId();

  /**
   *  Получает название задачи.
   *
   * @return name Название задачи.
   */
  String getName();

  /**
   *  Получает тип контента.
   *
   * @return contentType Тип контента, см. {@Link Content.Type}
   */
  Content.Type getContentType();

  /**
   *  Получает описание задачи.
   *
   * @return description Описание задачи.
   */
  String getDescription();

  /**
   *  Получает идентификатор приложенного к задаче файла.
   *
   * @return file Приложенные к задаче файлы, см. {@Link File}
   */
  int[] getFileId();

  /**
   *  Получает идентификатор автора задачи.
   *
   * @return authorId Идентификатор автора задачи.
   */
  int getAuthorId();

  /**
   *  Получает идентификатор исполнителя задачи.
   *
   * @return executorId Идентификатор исполнителя задачи.
   */
  int executorId();

  /**
   *  Получает дату создания задачи.
   *
   * @return dateCreated Дата создания задачи.
   */
  LocalDateTime getDateCreated();

  /**
   *  Получает дату окончания задачи.
   *
   * @return dateExpired Дата окончания задачи.
   */
  LocalDateTime getDateExpired();

  /**
   *  Получает идентификатор приложенного контента.
   *
   * @return contents Приложенный контент, см. {@Link Content}
   */
  int[] getContentsId();

  /**
   *  Получает идентификаторы комментариев к задаче.
   *
   * @return comments Комментарии к задаче, см. {@Link Comment}
   */
  int[] getCommentsId();

  /**
   *  Получает статус задачи.
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
