package com.htc.infrastructure.models;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.files.File;
import com.htc.domain.entities.tasks.Task;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Модель файлов для СУБД.
 */
@Entity
@Table(name = "Files")
@AllArgsConstructor
public class FileModel implements File {

  /**
   * Идентификатор файла.
   */
  @javax.persistence.Id
  @GeneratedValue
  private Integer fileId;

  @Override
  public Id getId() {
    return Id.create(fileId).get();
  }

  /**
   * Имя файла.
   */
  private String name;

  @Override
  public Name getName() {
    return File.Name.create(name).get();
  }

  /**
   * Дата загрузки файла.
   */
  private @Getter LocalDateTime dateCreated;

  /**
   * Формат файла.
   */
  private @Getter File.Format format;

  /**
   * Путь к файлу.
   */
  private String url;

  @Override
  public Url getUrl() {
    return Url.create(url).get();
  }

  /**
   * Родительская задача файла.
   */
  @ManyToOne
  @JoinColumn(name = "file_parent_task", referencedColumnName = "task_id", nullable = false)
  public @Getter TaskModel task;


  protected FileModel() {
  }
}
