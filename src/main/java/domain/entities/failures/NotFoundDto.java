package domain.entities.failures;

/**
 * Сущность не найдена.
 */
public class NotFoundDto implements Failure {
  private int statusCode;
  private String error;
}
