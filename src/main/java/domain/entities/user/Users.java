package domain.entities.user;

import domain.entities.user.Role.RoleType;

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
                "password",
                "example@mail.ru",
                new byte[]{},
                userRole
        );
    }
}
