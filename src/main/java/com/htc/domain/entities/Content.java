package com.htc.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Контент.
 */
public interface Content {

  /**
   *  Получает идентификатор контента.
   *
   * @return id Идентификатор контента.
   */
  Id getId();

  /**
   *  Получает тип контента.
   *
   * @return contentType Тип контента, см. {@Link Type}.
   */
  Type getType();

  /**
   *  Получает название контента.
   *
   * @return name Название контента.
   */
  String getName();

  /**
   *  Получает дату создания контента.
   *
   * @return dateCreateContent Дата создания контента.
   */
  LocalDateTime getDateCreated();

  /**
   *  Получает идентификатор автора контента.
   *
   * @return author Идентификатор автора контента.
   */
  int getAuthorId();

  /**
   *  Получает формат контента.
   *
   * @return format Формат контента, см. {@Link Format}
   */
  Format getFormat();

  /**
   *  Получает путь к файлу контента.
   *
   * @return url Путь к файлу контента.
   */
  String getUrlContent();

  /**
   *  Получает путь к превью контента.
   *
   * @return preview Путь к превью контента.
   */
  String getUrlPreview();

  /**
   *  Получает идентификатор задачи содержащей контент.
   *
   * @return taskId Идентификатор задачи содержащей контент.
   */
  int getTaskId();

  /**
   * Получает подтверждение добавления контента в ленту.
   *
   * @return approve Подтверждение добавления контента в ленту.
   */
  boolean getApprove();

  /**
   * Тип контента.
   */
  enum Type {

    /**
     * Видео.
     */
    @JsonProperty("video")
    VIDEO(1, "Видео"),
    /**
     * Аудио.
     */
    @JsonProperty("audio")
    AUDIO(2, "Аудио"),
    /**
     * Фото.
     */
    @JsonProperty("photo")
    PHOTO(3, "Фото");

    /**
     * Идентификатор типа контента.
     *
     * @return id Идентификатор типа контента.
     */
    @SuppressWarnings("JavadocDeclaration")
    private final @Getter int id;
    /**
     * Название типа контента.
     *
     * @return name Название типа контента.
     */
    @SuppressWarnings("JavadocDeclaration")
    private final @Getter String name;

    Type(int id, String name) {
      this.id = id;
      this.name = name;
    }
  }

  /**
   * Формат контента.
   */
  enum Format {
    @JsonProperty("jpg")
    JPG("JPG"),
    @JsonProperty("png")
    PNG("PNG"),
    @JsonProperty("mp3")
    MP3("MP3"),
    @JsonProperty("m4a")
    M4A("M4A"),
    @JsonProperty("flac")
    FLAC("FLAC"),
    @JsonProperty("avi")
    AVI("AVI"),
    @JsonProperty("mp4")
    MP4("MP4");

    /**
     * Формат контента.
     *
     * @return format Формат контента.
     */
    @SuppressWarnings("JavadocDeclaration")
    private final @Getter String format;

    Format(String format) {
      this.format = format;
    }
  }
}
