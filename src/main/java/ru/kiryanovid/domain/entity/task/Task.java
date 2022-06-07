package ru.kiryanovid.domain.entity.task;

import ru.kiryanovid.domain.entity.comment.Comment;
import ru.kiryanovid.domain.entity.content.Content;
import ru.kiryanovid.domain.entity.users.User;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.errors.InvalidValue;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.time.LocalDateTime;

/**
 * Задача
 */
@NoArgsConstructor
@AllArgsConstructor
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
    @Getter private Path file;

    /**
     * Автор задачи
     */
    @Getter private User author;

    /**
     * Дата создания задачи
     */
    @Getter LocalDateTime dateCreate;

    /**
     * Дата выполнения задачи
     */
    @Getter LocalDateTime dateExpired;

    /**
     * Контент
     */
    @Getter Content contents;

    /**
     * Комментарий
     */
    @Getter Iterable<Comment> comments;

    /**
     * Статус задачи
     */
    @Getter Status status;

    private static final String INVALID_ID = "Некорректный идентификатор.";
    private static final String INVALID_NAME = "Не допускается пустое поле.";
    private static final String INVALID_AUTHOR = "Отсутствует автор.";
    private static final String INVALID_DATE_EXPIRED = "Некорректная дата завершения.";
    private static final String INVALID_DATE_CREATE = "Некорректная дата создания.";

    /**
     * Создает задачу и проверят накорректность.
     * @param id Идентификатор
     * @param name Название
     * @param contentType Тип контента
     * @param description Описание
     * @param file Путь до файла
     * @param author Автор
     * @param dateCreate Дата создания
     * @param dateExpired Дата завершения
     * @param contents Контент
     * @param comments Комментарии
     * @return Задача
     */
    public static Either<Failure,Task> create(
            Integer id, String name, ContentType contentType, String description,
            Path file, User author, LocalDateTime dateCreate, LocalDateTime dateExpired, Content contents,Iterable<Comment> comments){

        if(id <=0){
            return Either.left(new InvalidValue(INVALID_ID));
        }
        if(name.isEmpty() || name.equals(" ")){
            return Either.left(new InvalidValue(INVALID_NAME));
        }
        if(author == null){
            return Either.left(new InvalidValue(INVALID_AUTHOR));
        }
        if(dateCreate.isBefore(LocalDateTime.now())){
            return Either.left(new InvalidValue(INVALID_DATE_CREATE));
        }
        if(dateCreate.isAfter(dateExpired)){
            return Either.left((new InvalidValue(INVALID_DATE_EXPIRED)));
        }


        var task = new Task();
        task.id = id;
        task.name = name;
        task.contentType = contentType;
        task.description = description;
        task.file = file;
        task. author = author;
        task.dateCreate = dateCreate;
        task.dateExpired = dateExpired;
        task.contents = contents;
        task.comments = comments;
        return Either.right(task);
    }
}
