package com.htc.application.dto.task;

import com.htc.application.dto.BaseResponse;
import com.htc.application.dto.DtoHelper;
import com.htc.application.dto.comment.CommentResponse;
import com.htc.application.dto.content.ContentResponse;
import com.htc.application.dto.file.FileResponse;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.task.TaskStatus;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;

/**
 * Представление задачи (ответ).
 */
public class TaskResponse implements BaseResponse {

  /**
   * Конструктор из соответствующей сущности.
   *
   * @param task Сущность {@link Task комментария}.
   */
  public TaskResponse(Task task) {
    this.id = task.getId();
    this.name = task.getName();
    this.type = task.getType();
    this.description = task.getDescription();
    this.author = new UserResponse(task.getAuthor());
    this.executor = new UserResponse(task.getExecutor());
    this.dateCreated = task.getDateCreated();
    this.dateExpired = task.getDateExpired();
    this.files = DtoHelper
            .createFromEntityList(task.getFiles(), FileResponse.class, File.class);
    this.contents = DtoHelper
            .createFromEntityList(task.getContents(), ContentResponse.class, Content.class);
    this.comments = DtoHelper
            .createFromEntityList(task.getComments(), CommentResponse.class, Comment.class);
    this.status = task.getStatus();
  }

  /**
   * Идентификатор задачи.
   */
  private final @Getter int id;

  /**
   * Имя задачи.
   */
  private final @Getter String name;

  /**
   * Тип задачи.
   */
  private final @Getter ContentType type;

  /**
   * Описание задачи.
   */
  private final @Getter String description;

  /**
   * Файлы, прикрепленные к задаче.
   */
  private final @Getter List<FileResponse> files;

  /**
   * Автор задачи.
   */
  private final @Getter UserResponse author;

  /**
   * Исполнитель задачи.
   */
  private final @Getter UserResponse executor;

  /**
   * Дата создания задачи.
   */
  private final @Getter LocalDate dateCreated;

  /**
   * Дата выполнения задачи (срок выполнения).
   */
  private final @Getter LocalDate dateExpired;

  /**
   * Контент, прикрепленный к задаче.
   */
  private final @Getter List<ContentResponse> contents;

  /**
   * Комментарии, прикрепленные к задаче.
   */
  private final @Getter List<CommentResponse> comments;

  /**
   * Статус задачи.
   */
  private final @Getter TaskStatus status;
}
