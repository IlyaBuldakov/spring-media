package domain.entities.user;

import domain.entities.user.UserRole.RoleType;

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
        return createTestUser(new Random().nextInt());
    }

    /**
     * Создание тестового пользователя с явно заданным идентификатором
     *
     * @param id Идентификатор
     * @return User Тестовый пользователь
     */
    public static User createTestUser(int id) {
        return createTestUser(id, new UserRole(1, UserRole.RoleType.ADMIN));
    }


    /**
     * Создание тестового пользователя с явно заданным
     * идентификатором и ролью
     *
     * @param id       Идентификатор
     * @param userRole Роль пользователя {@link RoleType}
     * @return User Тестовый пользователь
     */
    public static User createTestUser(int id, UserRole userRole) {
        return new User(
                id,
                "Тестовый Пользователь",
                "password",
                "example@mail.ru",
                new byte[]{},
                userRole
        );
    }
}
