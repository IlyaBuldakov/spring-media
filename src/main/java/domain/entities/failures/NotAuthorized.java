package domain.entities.failures;

/**
 * Ошибка авторизации
 *
 * @author IlyaBuldakov
 */
public class NotAuthorized extends Failure {
    public NotAuthorized(String message) {
        super(message);
    }
}
