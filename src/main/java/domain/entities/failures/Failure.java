package domain.entities.failures;

/**
 * Класс, описывающий сущность ошибки
 *
 * @author IlyaBuldakov
 */
public abstract class Failure {

    String message;

    public Failure(String message) {
        this.message = message;
    }
}
