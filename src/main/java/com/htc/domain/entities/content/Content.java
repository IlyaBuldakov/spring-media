package com.htc.domain.entities.content;

import com.htc.domain.entities.AbstractApplicationFormat;
import com.htc.domain.entities.ResponseConvertable;
import com.htc.domain.entities.user.User;
import java.time.LocalDate;

/**
 * Интерфейс, описывающий контент.
 */
public interface Content extends ResponseConvertable {

  /**
   * Перечисление форматов контента.
   */
  enum ContentFormat implements AbstractApplicationFormat {
    JPG,

    PNG,

    MP3,

    M4A,

    FLAC,

    AVI,

    MP4
  }


  /**
   * Идентификатор контента.
   *
   * @return Идентификатор контента.
   */
  Integer getId();

  /**
   * Тип контента.
   *
   * @return Тип контента.
   */
  ContentType getType();

  /**
   * Название контента.
   *
   * @return Название контента.
   */
  String getName();

  /**
   * Дата создания.
   *
   * @return Дата создания.
   */
  LocalDate getDateCreated();

  /**
   * Автор контента.
   *
   * @return Автор контента.
   */
  User getAuthor();

  /**
   * Формат контента {@link ContentFormat}.
   *
   * @return Формат контента.
   */
  ContentFormat getFormat();

  /**
   * Путь к файлу.
   *
   * @return Путь к файлу.
   */
  String getUrl();

  /**
   * Путь к превью.
   *
   * @return Путь к превью.
   */
  String getPreview();
}
