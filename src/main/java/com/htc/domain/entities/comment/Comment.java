package com.htc.domain.entities.comment;

import com.htc.domain.entities.ResponseConvertable;
import com.htc.domain.entities.user.User;
import java.time.LocalDate;

/**
 * Интерфейс, описывающий сущность комментария.
 */
public interface Comment extends ResponseConvertable {

  /**
   * Идентификатор комментария.
   *
   * @return Идентификатор комментария.
   */
  Integer getId();

  /**
   * Дата создания комментария.
   *
   * @return Дата создания.
   */
  LocalDate getDateCreated();

  /**
   * Автор комментария.
   *
   * @return Автор комментария.
   */
  User getAuthor();

  /**
   * Содержимое комментария.
   *
   * @return Сообщение комментария.
   */
  String getMessage();
}
