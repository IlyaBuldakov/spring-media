package domain.entities.failures;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ошибка запроса
 */
@AllArgsConstructor
public class BadRequestDto implements Failure {

    /**
     * @return Код статуса
     */
    private @Getter int statusCode;

    /**
     * @return Текст ошибки
     */
    private @Getter String error;

    /**
     * @return Проблемы запроса
     */
    private @Getter FieldInvalidDto problems;

}