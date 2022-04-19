package domain.entities.failures;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ошибка сервера
 */
@AllArgsConstructor
public class InternalServerErrorDto implements Failure {

    /**
     * @return Код статуса
     */
    private @Getter int statusCode;

    /**
     * @return Текст ошибки
     */
    private @Getter String error;

}