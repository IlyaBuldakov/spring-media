package com.htc.domain.entities.failures;

import lombok.Getter;

/**
 * Ошибка "Неверное значение".
 */
public enum InvalidValue implements Failure {

    // Пользователь
    INCORRECT_ID("id", "Некорректное значение идентификатора"),

    NEGATIVE_ID("id", "Идентификатор должен быть больше 0"),

    INCORRECT_USERNAME("name", "Некорректное имя пользователя"),

    INCORRECT_PASSWORD("password", "Пароль не соответствует требованиям"),

    INCORRECT_EMAIL("email", "Некорректный почтовый адрес"),

    INCORRECT_AVATAR("avatar", "Некорректный аватар"),

    // Задачи
    INCORRECT_TASKNAME("name", "Некорректное имя задачи"),

    INCORRECT_TASK_DESCRIPTION("description", "Некорректное описание задачи"),

    // Файлы
    INCORRECT_FORMAT("file", "Некорректный формат или файл повреждён"),

    EMPTY_FILE("file", "Пустой файл.");

    InvalidValue(String field, String message) {
        this.field = field;
        this.message = message;
    }

    /**
     * Поле, в котором обнаружена ошибка.
     */
    private final @Getter String field;

    /**
     * Сообщение об ошибке.
     */
    private final @Getter String message;
}
