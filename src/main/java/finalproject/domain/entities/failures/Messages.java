package finalproject.domain.entities.failures;


public enum Messages {
  INVALID_VALUES("Введенные данные некорректны"),
  INTERNAL_SERVER_ERROR("Ошибка сервера"),
  USERS_EMAIL_IS_ALREADY_EXISTS("Пользователь с таким e-mail уже существует"),
  AUTHORIZATION_FAILURE("Ошибка авторизации"),
  NOT_FOUND("Страница не найдена"),
  UNACCEPTABLE_FILE("Файл пустой или поврежденный"),
  UNACCEPTABLE_FILE_EXTENSION("Недопустимое расширение файла"),
  UNACCEPTABLE_FILE_CONTENT("Недопустимое содержимое файла"),
  UNACCEPTABLE_TASK_CONTENT("Контент не соответствует типу, установленному в задаче"),

  USER_NOT_FOUND("Пользователь не найден"),
  TASK_NOT_FOUND("Задача не найдена"),
  CONTENT_NOT_FOUND("Контент не найден"),
  FILE_NOT_FOUND("Файл не найден"),
  COMMENT_NOT_FOUND("Комментарий не найден"),
  NOT_ENOUGH_AUTHORITY("Недостаточно прав для выполнения операции"),




  ;


  final String stringMessage;

  Messages(String stringMessage) {
    this.stringMessage = stringMessage;
  }

}
