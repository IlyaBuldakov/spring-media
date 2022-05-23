package application.dto.content;

import domain.entity.task.ContentType;
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
    @Getter private ContentType name;
}
