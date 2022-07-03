package ru.kiryanovid.domain.entity.task;

import lombok.Getter;

/**
 * Тип контента
 */
public enum ContentType {
    VIDEO(1, "video"),
    AUDIO(2, "audio"),
    PHOTO(3, "photo");

    @Getter private final int id;
    @Getter private final String name;

    ContentType(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
