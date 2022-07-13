package finalproject.application.dto.failures;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ошибка валидации поля.
 */


public class FieldInvalidDto {

  @Getter
  String field;
  @Getter
  String message;

  public FieldInvalidDto(String field) {
    this.field = field;
    this.message = switch (field) {
      case "email" -> "Некорректный email";
      case "password" -> "Неправильный пароль";
      case "name" -> "Имя не введено";
      case "id" -> "Некорректный id";
      case "role" -> "Переданная роль не существует";
      default -> "Некорректное значение поля";
    };

  }
}
