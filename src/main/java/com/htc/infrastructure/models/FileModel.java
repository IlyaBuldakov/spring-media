package com.htc.infrastructure.models;

import com.htc.domain.entities.File;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * Модель файла для СУБД.
 */
@Entity
@Table(name = "Files")
public class FileModel implements File {
  /**
   * Идентификатор файла.
   */
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id", unique = true)
  private Integer fileId;

  @Override
  public Id getId() {
    return Id.create(this.fileId).get();
  }

  /**
   * Название файла.
   */
  private String name;

  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Дата создания файла.
   */
  private LocalDateTime dateCreated;

  @Override
  public LocalDateTime getDateCreated() {
    return this.dateCreated;
  }

  /**
   * Формат файла.
   */
  private Format format;

  @Override
  public Format getFormat() {
    return this.format;
  }

  /**
   * Путь к файлу.
   */
  private String urlFile;

  @Override
  public String getUrlFile() {
    return this.urlFile;
  }

  /**
   * Идентификатор задачи содержащей файл.
   */
  private int taskId;

  @Override
  public int getTaskId() {
    return this.taskId;
  }

  protected FileModel() {}

  public FileModel(String name, LocalDateTime dateCreated,
                   Format format, String urlFile, int taskId) {
    this(Id.create(0).get(), name, dateCreated, format, urlFile, taskId);
  }

  /**
   * Создает экземпляр класса {@Link FileModel}.
   *
   * @param id Идентификатор файла.
   * @param name Название файла.
   * @param dateCreated Дата создания файла.
   * @param format Формат файла.
   * @param urlFile Путь к файлу.
   * @param taskId Идентификатор задачи содержащей файл.
   */
  public FileModel(Id id, String name, LocalDateTime dateCreated,
                   Format format, String urlFile, int taskId) {
    this.fileId = id.getValue();
    this.name = name;
    this.dateCreated = dateCreated;
    this.format = format;
    this.urlFile = urlFile;
    this.taskId = taskId;
  }
}