package com.htc.domain.entities;

import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;

/**
 * Комментарий.
 */
public interface Comment {

  /**
   *  Идентификатор комментария.
   *
   * @return id Идентификатор комментария.
   */
  Id getId();

  /**
   *  Дата создания комментария.
   *
   * @return Дата создания комментария.
   */
  LocalDateTime getDateCreateComment();

  /**
   *  Идентификатор пользователя создавшего комментарий.
   *
   * @return Идентификатор пользователя создавшего комментарий.
   */
  int getUserId();

  /**
   *  Текст комментария.
   *
   * @return Текст комментария.
   */
  String getCommentMessage();

  /**
   *  Идентификатор задачи, в которой оставлен комментарий.
   *
   * @return Идентификатор задачи, в которой оставлен комментарий.
   */
  int taskId();
}
