package domain.entities.task;

import domain.entities.comment.CommentDto;
import domain.entities.content.ContentDto;
import domain.entities.content.ContentTypeDto;
import domain.entities.file.FileDto;
import domain.entities.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Задача
 */

@NoArgsConstructor
@AllArgsConstructor
public class TaskDto extends TaskBasicDto {

    /**
     * @return type Тип контента
     */
    private @Getter ContentTypeDto type;

    /**
     * @return description Описание задачи
     */
    private @Getter String description;

    /**
     * @return FileDto[] Файлы задачи
     */
    private @Getter FileDto[] files;

    /**
     * @return UserDto Автор задачи
     */
    private @Getter UserDto author;

    /**
     * @return UserDto Исполнитель задачи
     */
    private @Getter UserDto executor;

    /**
     * @return LocalDate дата создания задачи
     */
    private @Getter LocalDate dateCreated;

    /**
     * @return LocalDate Дата выполнения задачи
     */
    private @Getter LocalDate dateExpired;

    /**
     * @return ContentDto[] contents Приложенный контент
     */
    private @Getter ContentDto[] contents;

    /**
     * @return CommentDto[] comments Комментарии к задаче
     */
    private @Getter CommentDto[] comments;

    /**
     * @return TaskStatusDto status Статус задачи
     */
    private @Getter TaskStatusDto status;

}
