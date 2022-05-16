package domain.entities.failures;

/**
 * Проблемы запроса.
 */
public class BadRequestDto implements Failure {
  private int statusCode;
  private String error;
  private FieldInvalidDto[] problems;
}
