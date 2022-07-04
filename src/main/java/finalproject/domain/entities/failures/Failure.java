package finalproject.domain.entities.failures;


import lombok.Getter;
import lombok.Setter;

public class Failure {
  public enum Messages {
    INVALID_VALUES("Введенные данные некорректны"),
    USER_NOT_FOUND("Пользователь не найден"),
    INTERNAL_SERVER_ERROR("Ошибка сервера"),
    NOT_FOUND("Страница не найдена");
    final String stringMessage;
    Messages(String stringMessage) {
      this.stringMessage = stringMessage;
    }

  }

  @Setter
  private Messages message;

  @Getter
  @Setter
  private String[] problems;

  public String getMessage() {
    return this.message.stringMessage;
  }

  public Failure(Messages message) {
    this.message = message;
  }
  public Failure(Messages message, String[] problems) {
    this.message = message;
    this.problems = problems;
  }

}
