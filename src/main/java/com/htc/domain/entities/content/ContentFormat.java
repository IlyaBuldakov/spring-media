package com.htc.domain.entities.content;

import lombok.Getter;

/**
 * Форматы контента.
 */
public enum ContentFormat {
  JPG("JPG"),
  PNG("PNG"),
  MP3("MP3"),
  M4A("M4A"),
  FLAC("FLAC"),
  AVI("AVI"),
  MP4("MP4");

  /**
   * Формат контента.
   *
   * @return format Формат контента.
   */
  private final @Getter String format;

  ContentFormat(String format) {
    this.format = format;
  }
}
