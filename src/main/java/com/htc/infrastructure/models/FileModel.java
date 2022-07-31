package com.htc.infrastructure.models;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Entity;
import com.htc.domain.entities.File;
import com.htc.domain.entities.Notification;
import com.htc.domain.entities.attributes.Id;
import com.htc.infrastructure.exception.InvalidDataException;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Модель файлов для СУБД.
 */
@javax.persistence.Entity
@Table(name = "Files")
@AllArgsConstructor
public class FileModel implements Entity.Model<File> {

  /**
   * Идентификатор файла.
   */
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id")
  private Integer fileId;

  /**
   * Имя файла.
   */
  @NotNull
  private String name;

  /**
   * Дата загрузки файла.
   */
  @NotNull
  private LocalDateTime dateCreated;

  /**
   * Формат файла.
   */
  @NotNull
  private File.Format format;

  /**
   * Путь к файлу.
   */
  @NotNull
  @Column(unique = true)
  private String url;

  /**
   * Родительская задача файла.
   */
  @NotNull
  @ManyToOne
  public TaskModel task;


  protected FileModel() {
  }

  @Override
  public File toEntity() {
    final var id = Id
        .create(this.fileId)
        .getOrElseThrow(InvalidDataException::new);

    final var name = File.Name
        .create(this.name)
        .getOrElseThrow(InvalidDataException::new);

    final var url = File.Url
        .create(this.url)
        .getOrElseThrow(InvalidDataException::new);

    final var task = this.task.toEntity();

    return new File(id, name, dateCreated, format, url, task);
  }

  public FileModel(
      String name,
      LocalDateTime dateCreated,
      File.Format format,
      String url,
      TaskModel task) {
    this(0, name, dateCreated, format, url, task);
  }
}
