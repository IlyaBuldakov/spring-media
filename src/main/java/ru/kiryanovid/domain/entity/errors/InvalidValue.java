package ru.kiryanovid.domain.entity.errors;

/**
 * Не валидное значение
 */
public enum InvalidValue implements Failure{

    /**
     * Сообщение по умолчанию.
     */
    DEFAULT_MESSAGE("Некорректное значение переданного параметра."),

    INVALID_USER_NAME("Некорректное имя пользователя."),
    /**
     * Сообщение о некорректной электронной почте пользователя.
     */
    INVALID_USER_EMAIL("Некорректная электронная почта пользователя."),
    INVALID_USER_PASSWORD("Некорректный пароль пользователя."),
    INVALID_USER_IMAGE("Некорректное изображения пользователя."),

    // Сообщения общие для всех сущностей.
    ENTITY_ID_IS_NOT_A_NUMBER("Идентификатор сущности должен быть числом."),
    INVALID_TASK_NAME("Поле не может быть пустым."),
    INVALID_ENTITY_ID("Идентификатор сущности не может быть меньше нуля."),
    INVALID_DATE_EXPIRED("Некорректная дата завершения."),
    INVALID_DATE_CREATE("Некорректная дата создания."),
    INVALID_AUTHOR("Отсутствует автор.");

    private final String message;

    @Override
    public String getMessage() {
        return message;
    }

    InvalidValue(String message) {
        this.message = message;
    }
}
