package finalproject.domain.entities.failures;

/**
 * Ошибки, связанные с недостатком прав у пользователя.
 */
public class NotAuthorized extends Failure {
  public NotAuthorized(Messages message) {
    super(message);
  }
}
