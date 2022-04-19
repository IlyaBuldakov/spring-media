package domain.entities.failures;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ошибка валидации поля
 */
@AllArgsConstructor
public class FieldInvalidDto implements Failure {

    /**
     * @return Поле
     */
    private @Getter String field;

    /**
     * @return Сообщение об ошибке
     */
    private @Getter String message;



}
