package com.htc.domain.entities.task;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

/**
 * Класс, описывающий задачу
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public class Task {

    /**
     * Идентификатор задачи
     *
     * @return id Идентификатор задачи
     */
    private @Getter int id;

    /**
     * Название задачи
     *
     * @return name Название задачи
     */
    private @Getter String name;

    /**
     * Тип задачи
     *
     * @return type Тип задачи
     */
    private @Getter ContentType type;

    /**
     * Описание задачи
     *
     * @return description Описание задачи
     */
    private @Getter String description;

    /**
     * Приложенные файлы
     *
     * @return files Приложенные файлы
     */
    private @Getter File[] files;

    /**
     * Автор задачи
     *
     * @return author Автор задачи
     */
    private @Getter User author;

    /**
     * Исполнитель задачи
     *
     * @return executor Исполнитель задачи
     */
    private @Getter User executor;

    /**
     * Дата создания
     *
     * @return dateCreated Дата создания
     */
    private @Getter LocalDate dateCreated;

    /**
     * Дата выполнения
     *
     * @return dateExpired Дата выполнения
     */
    private @Getter LocalDate dateExpired;

    /**
     * Приложенный контент
     *
     * @return contents Список с контентом
     */
    private @Getter List<Content> contents;

    /**
     * Комментарии
     *
     * @return comments Список комментариев
     */
    private @Getter List<Comment> comments;

    /**
     * Статус задачи {@link TaskStatus.Status}
     *
     * @return status Статус задачи
     */
    private @Getter TaskStatus status;
}
