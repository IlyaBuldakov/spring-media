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

  /**
   * Конструктор ошибки некорректного заполнения поля запроса.
   *
   * @param field Поле, содержащее некорректное значение.
   */
  public FieldInvalidDto(String field) {
    this.field = field;
    this.message = switch (field) {
      case "email" -> "Некорректный email";
      case "password" -> "Неправильный пароль";
      case "name" -> "Имя не введено";
      case "id" -> "Некорректный id";
      case "taskId" -> "Некорректный id задачи";
      case "userId" -> "Некорректный id пользователя";
      case "role" -> "Переданная роль не существует";
      default -> "Некорректное значение поля";
    };

  }
}
