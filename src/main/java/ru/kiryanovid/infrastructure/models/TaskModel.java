package ru.kiryanovid.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.domain.entity.task.ContentType;
import ru.kiryanovid.domain.entity.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@AllArgsConstructor()
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
    @Transient
    @Getter private ContentType contentType;

    /**
     * Описание задачи
     */
    @Getter private String description;

    /**
     * Автор задачи
     */
    @Transient
    @Getter private User author;

    /**
     * Исполнитель задачи
     */
    @Transient
    @Getter private User executor;

    /**
     * Дата выполнения задачи
     */
    @Column(name = "data_executed")
    @Getter LocalDateTime dateExpired;

    protected TaskModel() {
    }
}
