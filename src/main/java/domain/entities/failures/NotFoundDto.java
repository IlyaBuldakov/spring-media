package domain.entities.failures;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ошибка поиска
 */
@AllArgsConstructor
public class NotFoundDto implements Failure {

    /**
     * @return Код статуса
     */
    private @Getter int statusCode;

    /**
     * @return Текст ошибки
     */
    private @Getter String error;