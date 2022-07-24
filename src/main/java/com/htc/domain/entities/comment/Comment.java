package com.htc.domain.entities.comment;

import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;

/**
 * Комментарий.
 */
public interface Comment {
  /**
   * Идентификатор комментария.
   *
   * @see Id#create(Long)
   *
   * @return id идентификатор
   */
  Id getId();

  /**
   * Дата создания.
   *
   * @see DateCreated#create()
   *
   * @return dateCreated дата создания
   */
  DateCreated getDateCreated();

  /**
   * {@link User Автор}.
   *
   * @return user автор
   */
  User getAuthor();

  /**
   * Текст комментария.
   *
   * @return message текст комментария
   */
  String getMessage();

  /**
   * {@link com.htc.domain.entities.content.Content Контент}.
   *
   * @return content контент (набор)
   */
  //TODO реализовать список контента
  int[] getContents();
}
