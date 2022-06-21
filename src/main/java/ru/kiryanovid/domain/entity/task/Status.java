package ru.kiryanovid.domain.entity.task;

import lombok.Getter;

/**
 * Статус контента.
 */
public enum Status {
    IN_WORK(1, "inWork"),
    FEEDBACK(2, "feedback"),
    APPROVED(3, "approved");

    @Getter private final int id;
    @Getter private final String name;

    Status(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
