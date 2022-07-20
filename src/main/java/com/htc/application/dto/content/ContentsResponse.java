package com.htc.application.dto.content;

import com.htc.domain.entities.Content;
import java.util.Collection;
import lombok.Getter;

/**
 * Представление сущности лента контента (ответ на запрос).
 */
public class ContentsResponse {

  /**
   * Лента контента.
   *
   * @return contents Лента контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Collection<Content> contents;

  /**
   * Количество контента в ленте.
   *
   * @return Количество контента в ленте.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int total;

  public ContentsResponse(Collection<Content> contents) {
    this.contents = contents.stream().toList();
    this.total = contents.size();
  }
}
