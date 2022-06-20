package ru.kiryanovid.domain.entity.task;

/**
 * Статус контента.
 */
public enum Status {
    IN_WORK(1, "inWork"),
    FEEDBACK(2, "feedback"),
    APPROVED(3, "approved");

    private int id;
    private String name;

    Status(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
