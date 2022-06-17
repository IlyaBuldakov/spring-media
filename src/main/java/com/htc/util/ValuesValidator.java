package com.htc.util;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValue;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Locale;

/**
 * Класс для валидации различных данных
 *
 * Возвращает Failure - в случае провала
 * и null - в случае успешной валидации
 *
 * @author IlyaBuldakov
 */
public class ValuesValidator {

    private static final String INVALID_ID = "Некорректный идентификатор";
    private static final String INVALID_NAME = "Некорректное имя пользователя";
    private static final String INVALID_PASSWORD = "Пароль не соответствует требованиям";
    private static final String INVALID_EMAIL = "Некорректный почтовый адрес";
    private static final String INVALID_AVATAR = "Некорректный аватар";

    /**
     * Метод валидации полей пользователя
     *
     * @param id Идентификатор
     * @param name Имя пользователя
     * @param password Пароль
     * @param email Электронная почта
     * @param avatar Аватар
     * @return Failure or null
     */
    public static Failure checkUserFields(int id,
                                          String name,
                                          String password,
                                          String email,
                                          byte[] avatar) {
        if (id < 0) {
            return new InvalidValue(INVALID_ID);
        }
        if (name.length() == 0) {
            return new InvalidValue(INVALID_NAME);
        }
        if (!password.matches("\\w{8,20}")
                || !password.matches(".*\\d+.*")
                || password.equals(password.toLowerCase(Locale.ROOT))
                || password.equals(password.toUpperCase(Locale.ROOT))) {
            return new InvalidValue(INVALID_PASSWORD);
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            return new InvalidValue(INVALID_EMAIL);
        }
        if (!Base64.isBase64(avatar)) {
            return new InvalidValue(INVALID_AVATAR);
        }
        return null;
    }
}
