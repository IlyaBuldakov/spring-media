package com.htc.application.dto.content;

import com.htc.domain.entities.content.Content;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * Представление сущности перечня медиаконтента.
 */
public class ContentsResponseDto {
  /**
   * Список медиаконтента.
   *
   * @return Список медиаконтента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Collection<ContentDto> contents;
  /**
   * Количество медиаконтента.
   *
   * @return Количество медиаконтента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int total;

  /**
   * Создаёт экземпляр класса {@link ContentsResponseDto}.
   *
   * @param contents Список медиаконтента.
   */
  public ContentsResponseDto(Collection<Content> contents) {
    this.contents = contents.stream()
            .map(ContentDto::new)
            .collect(Collectors.toList());
    this.total = contents.size();
  }
}
