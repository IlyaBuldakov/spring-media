package com.htc.util;

import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.failures.InvalidValuesContainer;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;

import java.util.Locale;

/**
 * Класс для валидации различных данных.
 *
 * Возвращает Failure - в случае провала
 * и null - в случае успешной валидации.
 */
public class ValuesValidator {

    private static final String BASE64_REGEX = "^([A-Za-z\\d+/]{4})*([A-Za-z\\d+/]{4}|[A-Za-z\\d+/]{3}=|[A-Za-z\\d+/]{2}==)?$";

    /**
     * Метод валидации всех полей пользователя.
     *
     * @param id       Идентификатор.
     * @param name     Имя пользователя.
     * @param password Пароль.
     * @param email    Электронная почта.
     * @param avatar   Аватар.
     * @return {@link InvalidValuesContainer} - контейнер с ошибками {@link InvalidValue}.
     */
    public static InvalidValuesContainer checkUserFields(
            String id, String name, String password, String email, String avatar) {
        InvalidValuesContainer invalidValues = new InvalidValuesContainer();
        IntegerValidator integerValidator = IntegerValidator.getInstance();
        if (!integerValidator.isValid(id)) {
            invalidValues.addInvalidValue(InvalidValue.INCORRECT_ID);
        }
        int paramToInt = Integer.parseInt(id);
        if (!integerValidator.minValue(paramToInt, 1)) {
            invalidValues.addInvalidValue(InvalidValue.NEGATIVE_ID);
        }
        if (name.length() == 0) {
            invalidValues.addInvalidValue(InvalidValue.INCORRECT_USERNAME);
        }
        if (!password.matches("\\w{8,20}")
                || !password.matches(".*\\d+.*")
                || password.equals(password.toLowerCase(Locale.ROOT))
                || password.equals(password.toUpperCase(Locale.ROOT))) {
            invalidValues.addInvalidValue(InvalidValue.INCORRECT_PASSWORD);
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            invalidValues.addInvalidValue(InvalidValue.INCORRECT_EMAIL);
        }
        if (!avatar.matches(BASE64_REGEX) || avatar.length() == 0) {
            invalidValues.addInvalidValue(InvalidValue.INCORRECT_AVATAR);
        }
        return invalidValues.getInvalidValues().size() == 0 ? null : invalidValues;
    }
}
