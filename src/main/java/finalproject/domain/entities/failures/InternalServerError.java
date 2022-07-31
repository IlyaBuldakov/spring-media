package finalproject.domain.entities.failures;

/**
 * Ошибки, которые не определяются явно.
 */
public class InternalServerError extends Failure {
  public InternalServerError(Messages message) {
    super(message);
  }
}
