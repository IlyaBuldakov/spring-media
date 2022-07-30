package com.htc.application.dto.content;

import com.htc.domain.entities.Content;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Представление сущности перечня медиаконтента.
 *
 * @param contents Список медиаконтента.
 * @param total Количество медиаконтента.
 */
public record ContentsResponseDto(
    Collection<ContentDto> contents,
    int total)
    /*implements Entity.View<ContentsResponseDto, Collection<Content>>*/ {
  /*@Override*/
  public ContentsResponseDto fromEntity(Collection<Content> contents) {
    return new ContentsResponseDto(contents);
  }

  /**
   * Создаёт экземпляр класса {@link ContentsResponseDto}.
   *
   * @param contents Список медиаконтента.
   */
  public ContentsResponseDto(Collection<Content> contents) {
    this(
        contents.stream()
            .map(ContentDto::new)
            .collect(Collectors.toList()),
        contents.size()
    );
  }
}
