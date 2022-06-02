package finalproject.application.dto.failures;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ошибка валидации поля.
 */
@AllArgsConstructor
public class FieldInvalidDto {


  /**
  * Возвращает @return ошибочное поле.
  */
  private @Getter String field;

  /**
  * Возвращает @return Сообщение об ошибке.
  */
  private @Getter String message;



}
