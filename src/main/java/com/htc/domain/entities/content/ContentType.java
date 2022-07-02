package com.htc.domain.entities.content;

import lombok.Getter;

/**
 * Тип контента.
 */
public enum ContentType {

  /**
   * Фото.
   */
  PHOTO(1, "Фото"),
  /**
   * Видео.
   */
  VIDEO(2, "Видео"),
  /**
   * Аудио.
   */
  AUDIO(3, "Аудио");
  /**
   * Идентификатор типа контента.
   *
   * @return id Идентификатор типа контента.
   */
  private final @Getter int id;

  /**
   * Наименование типа контента.
   *
   * @return Наименование типа контента.
   */
  private final @Getter String name;

  ContentType(int id, String name) {
    this.id = id;
    this.name = name;
  }
}

