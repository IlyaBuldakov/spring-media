package com.htc.domain.entities.content;

import lombok.Getter;

/**
 * Тип контента.
 */
public enum ContentType {
  /**
   * Фото.
   */
  PHOTO("Фото"),
  /**
   * Видео.
   */
  VIDEO("Видео"),
  /**
   * Аудио.
   */
  AUDIO("Аудио");

  /**
   * Наименование типа контента.
   *
   * @return Наименование типа контента.
   */
  private final @Getter String name;

  ContentType(String name) {
    this.name = name;
  }
}

