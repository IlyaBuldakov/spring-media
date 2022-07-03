package ru.kiryanovid.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.domain.entity.comment.Comment;
import ru.kiryanovid.domain.entity.content.Content;
import ru.kiryanovid.domain.entity.file.File;
import ru.kiryanovid.domain.entity.task.ContentType;
import ru.kiryanovid.domain.entity.task.Status;
import ru.kiryanovid.domain.entity.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor()
@Entity
@Table(name = "tasks")
public class TaskModel {
    /**
     * Идентификатор задачи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Transient
    @Getter private File file;

    /**
     * Автор задачи
     */
    @ManyToOne
    private @Getter User author;

    /**
     * Исполнитель задачи
     */
    @ManyToOne
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
    @Transient
    private @Getter Content content;

    /**
     * Комментарий
     */
    @Transient
    private @Getter Comment comment;

    /**
     * Статус задачи
     */
    private @Getter Status status;

    protected TaskModel() {
    }
}
