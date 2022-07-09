package ru.kiryanovid.domain.entity.task;

import io.vavr.control.Either;
import lombok.Getter;
import ru.kiryanovid.domain.entity.comment.Comment;
import ru.kiryanovid.domain.entity.content.Content;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.errors.InvalidValue;
import ru.kiryanovid.domain.entity.file.File;
import ru.kiryanovid.domain.entity.users.User;

import java.time.LocalDateTime;

/**
 * Задача
 */
public class Task {

    /**
     * Идентификатор задачи
     */
    @Getter private Integer id;

    /**
     * Название задачи
     */
    @Getter private String name;

    /**
     * Тип контента в задаче
     */
    @Getter private ContentType contentType;

    /**
     * Описание задачи
     */
    @Getter private String description;

    /**
     * Путь к файлу контента
     */
    @Getter private File file;

    /**
     * Автор задачи
     */
    private @Getter User author;

    /**
     * Исполнитель задачи
     */
    private @Getter User executor;

    /**
     * Дата создания задачи
     */
    private @Getter LocalDateTime dateCreate;

    /**
     * Дата выполнения задачи
     */
    private @Getter LocalDateTime dateExpired;

    /**
     * Контент
     */
    private @Getter Content content;

    /**
     * Комментарий
     */
    private @Getter Comment comment;

    /**
     * Статус задачи
     */
    private @Getter Status status;

    /**
     * Создает задачу и проверят на корректность.
     * @param id Идентификатор
     * @param name Название
     * @param contentType Тип контента
     * @param description Описание
     * @param file Путь до файла
     * @param author Автор
     * @param dateCreate Дата создания
     * @param dateExpired Дата завершения
     * @param content Контент
     * @param comment Комментарии
     * @return Задача
     */
    public static Either<Failure,Task> create(
            Integer id, String name, ContentType contentType, String description,
            File file, User author, User executor, LocalDateTime dateCreate, LocalDateTime dateExpired,
            Content content,Comment comment, Status status){

        if(name.isEmpty() || name.equals(" ")){
            return Either.left(InvalidValue.INVALID_TASK_NAME);
        }
        if(author == null){
            return Either.left(InvalidValue.INVALID_AUTHOR);
        }
        if(executor == null){
            return Either.left(InvalidValue.INVALID_AUTHOR);
        }
        if(dateCreate.isBefore(LocalDateTime.now())){
            return Either.left(InvalidValue.INVALID_DATE_CREATE);
        }
        if(dateCreate.isAfter(dateExpired)){
            return Either.left(InvalidValue.INVALID_DATE_EXPIRED);
        }


        var task = new Task();
        task.id = 0;
        task.name = name;
        task.contentType = contentType;
        task.description = description;
        task.file = file;
        task.author = author;
        task.executor = executor;
        task.dateCreate = dateCreate;
        task.dateExpired = dateExpired;
        task.content = content;
        task.comment = comment;
        task.status = status;
        return Either.right(task);
    }
}
