package finalproject.application.dto.failures;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ошибка валидации поля.
 */

@AllArgsConstructor
public class FieldInvalidDto {
  String field;
}
