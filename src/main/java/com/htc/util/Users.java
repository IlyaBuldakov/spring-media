package com.htc.util;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.Role.RoleType;
import com.htc.domain.entities.user.User;
import org.apache.commons.codec.binary.Base64;

import java.util.Random;

/**
 * Утилитный класс для сущности пользователя
 *
 * @author IlyaBuldakov
 */
public class Users {

    /**
     * Создание тестового пользователя с рандомным идентификатором
     *
     * @return User Тестовый пользователь
     */
    public static User createTestUser() {
        return createTestUser(new Random().nextInt(255));
    }

    /**
     * Создание тестового пользователя с явно заданным идентификатором
     *
     * @param id Идентификатор
     * @return User Тестовый пользователь
     */
    public static User createTestUser(int id) {
        return createTestUser(id, new Role(1, Role.RoleType.ADMIN));
    }


    /**
     * Создание тестового пользователя с явно заданным
     * идентификатором и ролью
     *
     * @param id       Идентификатор
     * @param role     Роль пользователя {@link RoleType}
     * @return User    Тестовый пользователь
     */
    public static User createTestUser(int id, Role role) {
        return User.create(
                id,
                "Тестовый Пользователь",
                "password1A",
                "example@mail.ru",
                Base64.decodeBase64("SGVsbG9Xb3JsZA=="),
                role
        ).get();
    }

    /**
     * Получение случайной роли
     * @return Role Роль пользователя
     */
    public static Role getRandomTestRole() {
        var values = RoleType.values();
        Random random = new Random();
        return new Role(1, values[random.nextInt(values.length)]);
    }
}
