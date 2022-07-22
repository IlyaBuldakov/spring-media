package com.htc.util;

import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.failures.InvalidValuesContainer;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;

import java.util.Locale;

/**
 * Класс для валидации различных данных.
 */
public class ValuesValidator {

    /**
     * Регулярное выражение для проверки аватара
     * на соответствие Base64.
     */
    private static final String BASE64_REGEX = "^([A-Za-z\\d+/]{4})*([A-Za-z\\d+/]{4}|[A-Za-z\\d+/]{3}=|[A-Za-z\\d+/]{2}==)?$";

    /**
     * Метод валидации идентификатора типа String.
     *
     * @param id Идентификатор.
     * @return {@link InvalidValuesContainer} - контейнер с ошибками {@link InvalidValue}.
     */
    public static InvalidValuesContainer validateStringId(String id) {
        InvalidValuesContainer invalidValues = new InvalidValuesContainer();
        IntegerValidator integerValidator = IntegerValidator.getInstance();
        if (!integerValidator.isValid(id)) {
            invalidValues.addInvalidValue(InvalidValue.INCORRECT_ID);
            return invalidValues;
        }
        int paramToInt = Integer.parseInt(id);
        if (!integerValidator.minValue(paramToInt, 1)) {
            invalidValues.addInvalidValue(InvalidValue.NEGATIVE_ID);
        }
        return invalidValues.getInvalidValues().size() == 0 ? null : invalidValues;
    }

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
        var validateId = validateStringId(id);
        if (validateId != null) {
            invalidValues = validateId;
        }
        var anotherFields = checkUserFields(name, password, email, avatar);
        if (anotherFields != null) {
            invalidValues.merge(anotherFields);
        }
        return invalidValues.getInvalidValues().size() == 0 ? null : invalidValues;
    }

    /**
     * Метод валидации всех полей пользователя кроме идентификатора.
     *
     * @param name     Имя пользователя.
     * @param password Пароль.
     * @param email    Электронная почта.
     * @param avatar   Аватар.
     * @return {@link InvalidValuesContainer} - контейнер с ошибками {@link InvalidValue}.
     */
    public static InvalidValuesContainer checkUserFields(
            String name, String password, String email, String avatar) {
        InvalidValuesContainer invalidValues = new InvalidValuesContainer();
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

    /**
     * Метод валидации полей задачи.
     *
     * @param id Идентификатор задачи.
     * @param name Имя задачи.
     * @param description Описание задачи.
     * @param authorId Идентификатор автора задачи.
     * @param executorId Идентификатор исполнителя задачи.
     * @return Контейнер ошибок или null;
     */
    public static InvalidValuesContainer checkTaskFields(
            String id, String name, String description, String authorId, String executorId) {
        InvalidValuesContainer invalidValues = new InvalidValuesContainer();
        var validateId = validateStringId(id);
        if (validateId != null) {
            invalidValues = validateId;
        }
        var anotherFields = checkTaskFields(name, description, authorId, executorId);
        if (anotherFields != null) {
            invalidValues.merge(anotherFields);
        }
        return invalidValues.getInvalidValues().size() == 0 ? null : invalidValues;
    }

    /**
     * Метод валидации полей задачи, кроме идентификатора.
     *
     * @param name Имя задачи.
     * @param description Описание задачи.
     * @param authorId Идентификатор автора задачи.
     * @param executorId Идентификатор исполнителя задачи.
     * @return Контейнер ошибок или null;
     */
    public static InvalidValuesContainer checkTaskFields(String name, String description, String authorId, String executorId) {
        InvalidValuesContainer invalidValues = new InvalidValuesContainer();
        var validateAuthor = validateStringId(authorId);
        var validateExecutor = validateStringId(executorId);
        if (validateAuthor != null) {
            invalidValues.merge(validateAuthor);
        }
        if (validateExecutor != null) {
            invalidValues.merge(validateExecutor);
        }
        if (name.length() <= 0 || name.length() > 255) {
            invalidValues.addInvalidValue(InvalidValue.INCORRECT_TASKNAME);
        }
        if (description.length() <= 10) {
            invalidValues.addInvalidValue(InvalidValue.INCORRECT_TASK_DESCRIPTION);
        }
        return invalidValues.getInvalidValues().size() == 0 ? null : invalidValues;
    }
}
