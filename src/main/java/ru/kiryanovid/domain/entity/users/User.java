package ru.kiryanovid.domain.entity.users;


import io.vavr.control.Either;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.validator.routines.EmailValidator;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.errors.InvalidValue;

/**
 * Пользователь.
 */
public class User {
    /**
     * Идентификатор пользователя. Идентификатор - неотрицательное число.
     *
     * @return id Идентификатор пользователя.
     */
    private @Getter int id;

    /**
     * Имя пользователя. Имя - непустая строка.
     *
     * @return id Имя пользователя.
     */
    private @Getter String name;

    /**
     * Электронная почта пользователя.
     *
     * @return id Электронная почта пользователя.
     */
    private @Getter String email;

    /**
     * Пароль пользователя.
     *
     * <p>
     * Корректный пароль:
     * 1. длина пароля от 8 до 20 символов (включительно),
     * 2. пароль содержащий символы латинского алфавита обоих регистров,
     * 3. пароль содержим цифры,
     * 4. пароль может содержать знак подчёркивания.
     * </p>
     *
     * <p>
     * Пример корректного пароля: {@code abcdEFG1234}
     * Пример некорректного пароля: {@code abc}
     * </p>
     *
     * @return id Пароль пользователя.
     */
    private @Getter String password;

    /**
     * Изображение пользователя. Изображение представлено в кодировке Base64.
     *
     * @return id Изображение пользователя.
     */
    private @Getter String image;

    /**
     * Роль пользователя, см. {@link Role}.
     *
     * @return id Роль пользователя.
     */
    private @Getter Role role;

    private User() {}

    private static final String INVALID_ID = "Некорректный идентификатор.";
    private static final String INVALID_NAME = "Некорректное имя.";
    private static final String INVALID_EMAIL = "Некорректная электронная почта.";
    private static final String INVALID_PASSWORD = "Некорректный пароль.";
    private static final String INVALID_IMAGE = "Некорректное изображение.";

    /**
     * Создаёт пользователя и проверяет данные на корректность.
     *
     * @param id Идентификатор.
     * @param name Имя.
     * @param email Электронная почта.
     * @param password Пароль.
     * @param image Изображение.
     * @param role Роль.
     * @return Пользователь.
     */
    public static Either<Failure, User> create(
            int id, String name, String email, String password, String image, Role role) {
        // Проверка идентификатора.
        if (id < 0) {
            return Either.left(InvalidValue.INVALID_ENTITY_ID);
        }

        // Проверка имени.
        if (name.length() == 0) {
            return Either.left(InvalidValue.INVALID_USER_NAME);
        }

        // Проверка почты.
        if (email.length() == 0 || !EmailValidator.getInstance().isValid(email)) {
            return Either.left(InvalidValue.INVALID_USER_EMAIL);
        }

        // Проверка пароля.
        if (!password.matches("\\w{8,20}")
                || !password.matches(".*\\d+.*")
                || password.toLowerCase().equals(password)
                || password.toUpperCase().equals(password)) {
            return Either.left(InvalidValue.INVALID_USER_PASSWORD);
        }

        // Проверка изображения.
        if (image.length() == 0 || !Base64.isBase64(image)) {
            return Either.left(InvalidValue.INVALID_USER_IMAGE);
        }

        var user = new User();
        user.id = id;
        user.name = name;
        user.email = email;
        user.password = password;
        user.image = image;
        user.role = role;
        return Either.right(user);
    }
}
