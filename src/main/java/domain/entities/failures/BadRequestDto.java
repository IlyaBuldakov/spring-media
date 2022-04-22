package domain.entities.failures;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ошибка запроса.
 */
@AllArgsConstructor
public class BadRequestDto implements Failure {

  /**
   * Возвращает @return код статуса.
   */
  private @Getter int statusCode;

  /**
   * Возвращает @return текст ошибки.
   */
  private @Getter String error;

  /**
   * Возвращает @return проблемы запроса.
   */
  private @Getter FieldInvalidDto problems;

}