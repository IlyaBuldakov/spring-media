package domain.entities.failures;

/**
 * Не авторизован.
 */
public class NotAuthorizedDto implements Failure {
  private int statusCode;
  private String error;
}
