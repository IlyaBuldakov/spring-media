package ru.kiryanovid.domain.entity.users;

import lombok.Getter;

/**
 *
 */
public enum Role {
    ADMIN(1,"administrator"),
    MANAGER(2, "manager"),
    CONTENT_MAKER(3, "content-maker");

    @Getter private final int id;
    @Getter private final String name;

    Role(int id, String name){
        this.id = id;
        this.name = name;
    }
}
