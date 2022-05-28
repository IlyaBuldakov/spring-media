package domain.entities.user;

import domain.entities.failures.Failure;
import domain.entities.failures.InvalidValue;
import io.vavr.control.Either;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Locale;

/**
 * Класс, описывающий пользователя
 *
 * @author IlyaBuldakov
 */

public class User {

    private User() {}

    /**
     * Идентификатор пользователя
     *
     * @return id Идентификатор пользователя
     */
    private @Getter int id;

    /**
     * Имя пользователя
     *
     * @return name Имя пользователя
     */
    private @Getter String name;

    /**
     * Пароль пользователя
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
     * @return password Пароль пользователя
     */
    private @Getter String password;

    /**
     * E-mail пользователя
     *
     * @return email E-mail пользователя
     */
    private @Getter String email;

    /**
     * Аватар пользователя
     *
     * @return avatar Аватар пользователя
     */
    private @Getter byte[] avatar;

    /**
     * Роль пользователя {@link Role.RoleType}
     *
     * @return role Роль пользователя
     */
    private @Getter Role role;

    private static final String INVALID_ID = "Некорректный идентификатор";
    private static final String INVALID_NAME = "Некорректное имя пользователя";
    private static final String INVALID_PASSWORD = "Пароль не соответствует требованиям";
    private static final String INVALID_EMAIL = "Некорректный почтовый адрес";
    private static final String INVALID_AVATAR = "Некорректный аватар";

    /**
     * Фабричный метод пользователя
     *
     * @param id       Идентификатор
     * @param name     Имя пользователя
     * @param password Пароль
     * @param email    Почта
     * @param avatar   Аватар
     * @return Пользователь
     */

    public static Either<Failure, User> create(int id,
                                               String name,
                                               String password,
                                               String email,
                                               byte[] avatar,
                                               Role role) {
        if (id < 0) {
            return Either.left(new InvalidValue(INVALID_ID));
        }
        if (name.length() == 0) {
            return Either.left(new InvalidValue(INVALID_NAME));
        }
        if (!password.matches("\\w{8,20}")
                || !password.matches(".*\\d+.*")
                || password.equals(password.toLowerCase(Locale.ROOT))
                || password.equals(password.toUpperCase(Locale.ROOT))) {
            return Either.left(new InvalidValue(INVALID_PASSWORD));
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            return Either.left(new InvalidValue(INVALID_EMAIL));
        }
        if (!Base64.isBase64(avatar)) {
            return Either.left(new InvalidValue(INVALID_AVATAR));
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
