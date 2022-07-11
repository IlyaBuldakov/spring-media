package com.htc.domain.entities.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.util.ValuesValidator;
import io.vavr.control.Either;
import lombok.Getter;

/**
 * Класс, описывающий пользователя.
 */
public class User {

    private User() {}

    /**
     * Идентификатор пользователя.
     *
     * @return Идентификатор пользователя.
     */
    private @Getter int id;

    /**
     * Имя пользователя.
     *
     * @return Имя пользователя.
     */
    private @Getter String name;

    /**
     * Пароль пользователя.
     * <p>
     * Требования к паролю:
     * 1. Длина пароля от 8 до 20 символов (включительно),
     * 2. Пароль должен содержать символы латинского алфавита обоих рег-ров,
     * 3. Пароль должен содержать хотя бы одну цифру,
     * 4. Пароль может содержать знак подчёркивания.
     * <p>
     * Корректный пароль: 1aaAbbBccC
     * Некорректный пароль: aaa
     *
     * @return Пароль пользователя.
     */
    private @Getter String password;

    /**
     * E-mail пользователя.
     *
     * @return E-mail пользователя.
     */
    private @Getter String email;

    /**
     * Аватар пользователя.
     *
     * @return Аватар пользователя.
     */
    private @Getter String avatar;

    /**
     * Роль пользователя {@link Role.RoleType}.
     *
     * @return Роль пользователя.
     */
    private @Getter Role role;

    /**
     * Фабричный метод пользователя.
     *
     * @param id       Идентификатор.
     * @param name     Имя пользователя.
     * @param password Пароль.
     * @param email    Почта.
     * @param avatar   Аватар.
     * @return Пользователь.
     */

    public static Either<Failure, User> create(int id,
                                               String name,
                                               String password,
                                               String email,
                                               String avatar,
                                               Role role) {
        Failure expectedFailure = ValuesValidator
                .checkUserFields(id, name, password, email, avatar);

        if (expectedFailure != null) {
            return Either.left(expectedFailure);
        }

        User user = new User();
        user.id = id;
        user.name = name;
        user.email = email;
        user.password = password;
        user.avatar = avatar;
        user.role = role;
        return Either.right(user);
    }
}
