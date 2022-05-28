package com.htc.domain.entities.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Класс, описывающий статус задачи
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public class TaskStatus {

    /**
     * Перечисление возможных статусов задачи
     */
    public enum Status {
        /**
         * В работе
         */
        IN_WORK,

        /**
         * Ожидает согласования
         */
        FEEDBACK,

        /**
         * Выполнено
         */
        APPROVED
    }


    /**
     * Идентификатор статуса задачи
     *
     * @return id Идентификатор статуса задачи
     */
    private @Getter int id;

    /**
     * Название статуса задачи
     *
     * @return name Название статуса задачи
     */
    private @Getter Status name;
}
