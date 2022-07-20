package com.htc.domain.entities.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Класс, описывающий статус задачи.
 */
@AllArgsConstructor
public enum TaskStatus {

        /**
         * В работе.
         */
        IN_WORK(1, "В работе"),

        /**
         * Ожидает согласования.
         */
        FEEDBACK(2, "Ожидает согласования"),

        /**
         * Выполнено.
         */
        APPROVED(3, "Выполнено");

    /**
     * Идентификатор статуса задачи.
     *
     * @return Идентификатор статуса задачи.
     */
    private final @Getter int id;

    /**
     * Название статуса задачи.
     *
     * @return Название статуса задачи.
     */
    private final @Getter String name;
}
