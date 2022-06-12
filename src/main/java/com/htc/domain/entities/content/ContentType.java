package com.htc.domain.entities.content;

import lombok.Getter;

/**
 * Тип контента.
 */
public enum ContentType {

  /**
   * Видео.
   */
  VIDEO(1, "Видео"),
  /**
   * Аудио.
   */
  AUDIO(2, "Аудио"),
  /**
   * Фото.
   */
  PHOTO(3, "Фото");

  /**
   * Идентификатор типа контента.
   *
   * @return id Идентификатор типа контента.
   */
  private final @Getter int id;
  /**
   * Название типа контента.
   *
   * @return name Название типа контента.
   */
  private final @Getter String name;

  ContentType(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
