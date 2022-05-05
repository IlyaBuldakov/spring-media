package domain.entity.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public class FieldMessageDto implements Failure{
    /**
     *
     */
    @Getter private String field;

    /**
     *
     */
    @Getter private String message;
}
