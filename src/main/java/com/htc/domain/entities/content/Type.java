package com.htc.domain.entities.content;

import lombok.Getter;

/**
 * Тип контента.
 */
public enum Type {
  VIDEO(1, "Видео"),
  AUDIO(2, "Аудио"),
  PHOTO(3, "Фото"),
  COMMENT(4, "Комментарий"),
  CONTENT(5, "Контент");

  /**
   * Идентификатор типа контента.
   *
   * @return id идентификатор типа
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;

  /**
   * Наименование типа контента.
   *
   * @return name наименование типа
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  Type(int id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * Получение типа контента по названию контента.
   *
   * @param name название контента
   * @return type {@link Type тип}
   */
  public static Type getFromName(String name) {
    for (Type type : Type.values()) {
      if (type.getName().equals(name))  {
        return type;
      }
    }
    return null;
  }
}