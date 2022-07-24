package com.htc.domain.entities;

import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;

/**
 * Комментарий.
 */
public interface Comment {

  /**
   *  Получает идентификатор комментария.
   *
   * @return id Идентификатор комментария.
   */
  Id getId();

  /**
   *  Получает дату создания комментария.
   *
   * @return Дата создания комментария.
   */
  LocalDateTime getDateCreateComment();

  /**
   *  Получает идентификатор пользователя создавшего комментарий.
   *
   * @return Идентификатор пользователя создавшего комментарий.
   */
  int getUserId();

  /**
   *  Получает текст комментария.
   *
   * @return Текст комментария.
   */
  String getCommentMessage();

  /**
   *  Получает идентификатор задачи, в которой оставлен комментарий.
   *
   * @return Идентификатор задачи, в которой оставлен комментарий.
   */
  int taskId();
}
