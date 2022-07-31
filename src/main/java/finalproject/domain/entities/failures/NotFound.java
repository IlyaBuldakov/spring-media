package finalproject.domain.entities.failures;

/**
 * Ошибка "сущность не найдена".
 */
public class NotFound extends Failure {
  public NotFound(Messages message) {
    super(message);
  }
}
