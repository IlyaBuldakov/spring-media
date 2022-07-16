package com.htc.domain.entities.user;

import lombok.Getter;

/**
 * Класс, описывающий роль пользователя.
 */
public enum Role {

    ADMIN(1, "Администратор"),

    MANAGER(2, "Менеджер"),

    CONTENT_MAKER(3, "Контент-мейкер");

    Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private final @Getter int id;

    private final @Getter String name;
}
