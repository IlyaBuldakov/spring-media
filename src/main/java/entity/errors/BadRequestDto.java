package entity.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public class BadRequestDto {
    /**
     *
     */
    @Getter private Integer statusCode;

    /**
     *
     */
    @Getter private String error;

    /**
     *
     */
    @Getter private FieldMessageDto problems;

}
