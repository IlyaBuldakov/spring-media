package domain.entities.failures;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ошибка валидации поля.
 */
@AllArgsConstructor
public class FieldInvalidDto implements Failure {


  /**
  * Возвращает @return ошибочное поле.
  */
  private @Getter String field;

  /**
  * Возвращает @return Сообщение об ошибке.
  */
  private @Getter String message;



}
