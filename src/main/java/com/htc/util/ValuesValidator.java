package com.htc.util;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValue;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Locale;

/**
 * Класс для валидации различных данных.
 *
 * Возвращает Failure - в случае провала
 * и null - в случае успешной валидации.
 */
public class ValuesValidator {

    private static final String BASE64_REGEX = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$";

    /**
     * Метод валидации полей пользователя.
     *
     * @param id Идентификатор.
     * @param name Имя пользователя.
     * @param password Пароль.
     * @param email Электронная почта.
     * @param avatar Аватар.
     * @return {@link Failure} или null.
     */
    public static Failure checkUserFields(int id,
                                          String name,
                                          String password,
                                          String email,
                                          String avatar) {
        if (id < 0) {
            return InvalidValue.NEGATIVE_ID;
        }
        if (name.length() == 0) {
            return InvalidValue.INCORRECT_USERNAME;
        }
        if (!password.matches("\\w{8,20}")
                || !password.matches(".*\\d+.*")
                || password.equals(password.toLowerCase(Locale.ROOT))
                || password.equals(password.toUpperCase(Locale.ROOT))) {
            return InvalidValue.INCORRECT_PASSWORD;
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            return InvalidValue.INCORRECT_EMAIL;
        }
        if (!avatar.matches(BASE64_REGEX) || avatar.length() == 0) {
            return InvalidValue.INCORRECT_AVATAR;
        }
        return null;
    }
}
