package finalproject.domain.entities.failures;


import java.util.List;
import lombok.Getter;
import lombok.Setter;

/** Общий класс всех ошибок.
 *
 */
public class Failure {

  /**
   * Перечисление возможных ошибок.
   */
  public enum Messages {
    INVALID_VALUES("Введенные данные некорректны"),
    ENTITY_NOT_FOUND("Сущность не найдена"),
    INTERNAL_SERVER_ERROR("Ошибка сервера"),
    USERS_EMAIL_IS_ALREADY_EXISTS("Пользователь с таким e-mail уже существует"),
    AUTHORIZATION_FAILURE("Ошибка авторизации"),
    NOT_FOUND("Страница не найдена"),
    UNACCEPTABLE_FILE_FORMAT("Недопустимый формат файла");


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
  private List<String> problemList;

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

  public Failure(Messages message, List<String> problemList) {
    this.message = message;
    this.problems = problemList.toArray(new String[0]);
  }

}
