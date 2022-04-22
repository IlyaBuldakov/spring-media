package domain.entities.failures;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ошибка поиска.
 */
@AllArgsConstructor
public class NotFoundDto implements Failure {

  /**
   * Возвращает @return код статуса.
   */
  private @Getter int statusCode;

  /**
   * Возвращает @return текст ошибки.
   */
  private @Getter String error;

}