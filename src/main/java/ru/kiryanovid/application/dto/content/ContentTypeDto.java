package ru.kiryanovid.application.dto.content;

import ru.kiryanovid.domain.entity.task.ContentType;
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
