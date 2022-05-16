package domain.entities.failures;

/**
 * Внутренняя ошибка сервера.
 */
public class InternalServerErrorDto implements Failure {
  private int statusCode;
  private String error;

}
