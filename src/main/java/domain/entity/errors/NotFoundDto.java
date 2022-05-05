package domain.entity.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public class NotFoundDto implements Failure{
    /**
     * код статуса ошибки
     */
    @Getter
    private Integer statusCode;

    /**
     * Текст ошибки
     */
    @Getter private String error;
}
