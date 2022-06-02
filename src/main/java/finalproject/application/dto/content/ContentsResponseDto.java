package finalproject.application.dto.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ответ на запрос контента.
 */
@AllArgsConstructor
public class ContentsResponseDto {

  /**
   * Возвращает @return ContentDto[] массив единиц контента.
   */
  private @Getter ContentDto[] contents;

  /**
   * Возвращает @return total количество единиц контента.
   */
  private @Getter int total;


}