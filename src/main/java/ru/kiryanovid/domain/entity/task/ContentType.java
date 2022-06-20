package ru.kiryanovid.domain.entity.task;

/**
 * Тип контента
 */
public enum ContentType {
    VIDEO(1, "video"),
    AUDIO(2, "audio"),
    PHOTO(3, "photo");

    private int id;
    private String name;

    ContentType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
