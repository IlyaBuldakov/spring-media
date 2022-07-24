package ru.kiryanovid.application.dto.content;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Представление сущности контента (запрос).
 */
@AllArgsConstructor
public class ContentsResponseDto {
  /**
  * Список контента в ответе.
  */
  @Getter private List<ContentDto> contents;

  /**
  * Количество контента в ответе.
  */
  @Getter private Integer total;
}
