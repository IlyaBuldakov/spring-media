package domain.entities.failures;

/**
 * Ощибка поля.
 */
public class FieldInvalidDto implements Failure {
  private String field;
  private String message;
}
