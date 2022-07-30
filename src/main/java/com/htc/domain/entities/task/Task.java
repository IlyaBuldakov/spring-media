package com.htc.domain.entities.task;

import com.htc.domain.entities.ResponseConvertable;
import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.user.User;
import java.time.LocalDate;
import java.util.List;

/**
 * Интерфейс, описывающий задачу.
 */
public interface Task extends ResponseConvertable {

  /**
   * Идентификатор задачи.
   *
   * @return Идентификатор задачи.
   */
  Integer getId();

  /**
   * Название задачи.
   *
   * @return Название задачи.
   */
  String getName();

  /**
   * Тип задачи.
   *
   * @return Тип задачи.
   */
  ContentType getType();

  /**
   * Описание задачи.
   *
   * @return Описание задачи.
   */
  String getDescription();

  /**
   * Приложенные файлы.
   *
   * @return Приложенные файлы.
   */
  List<? extends File> getFiles();

  /**
   * Автор задачи.
   *
   * @return Автор задачи.
   */
  User getAuthor();

  /**
   * Исполнитель задачи.
   *
   * @return Исполнитель задачи.
   */
  User getExecutor();

  /**
   * Дата создания.
   *
   * @return Дата создания.
   */
  LocalDate getDateCreated();

  /**
   * Дата выполнения.
   *
   * @return Дата выполнения.
   */
  LocalDate getDateExpired();

  /**
   * Приложенный контент.
   *
   * @return Список с контентом.
   */
  List<? extends Content> getContents();

  /**
   * Комментарии.
   *
   * @return Список комментариев.
   */
  List<? extends Comment> getComments();

  /**
   * Статус задачи.
   *
   * @return Статус задачи.
   */
  TaskStatus getStatus();
}
