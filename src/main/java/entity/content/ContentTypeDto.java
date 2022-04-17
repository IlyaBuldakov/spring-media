package entity.content;

import entity.task.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public class ContentTypeDto {
    /**
     *
     */
    @Getter private Integer id;

    /**
     *
     */
    @Getter private Type name;
}
