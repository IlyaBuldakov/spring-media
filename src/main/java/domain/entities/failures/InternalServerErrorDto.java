package domain.entities.failures;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ошибка сервера.
 */
@AllArgsConstructor
public class InternalServerErrorDto implements Failure {

  /**
   * Возвращает @return код статуса.
   */
  private @Getter int statusCode;

  /**
   * Возвращает @return текст ошибки.
   */
  private @Getter String error;

}