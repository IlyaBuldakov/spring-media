package com.htc.infrastructure.models;

import com.htc.domain.entities.File;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

  /**
   * Название файла.
   */
  private String name;

  /**
   * Дата создания файла.
   */
  private LocalDateTime dateCreated;

  /**
   * Формат файла.
   */
  @Enumerated(EnumType.STRING)
  private Format format;

  /**
   * Путь к файлу.
   */
  private String urlFile;

  /**
   * Идентификатор задачи содержащей файл.
   */
  private int taskId;

  @Override
  public Id getId() {
    return Id.create(this.fileId).get();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public LocalDateTime getDateCreated() {
    return this.dateCreated;
  }

  @Override
  public Format getFormat() {
    return this.format;
  }

  @Override
  public String getUrlFile() {
    return this.urlFile;
  }

  @Override
  public int getTaskId() {
    return this.taskId;
  }

  /**
   * Создаёт экземпляр класса {@link FileModel}.
   */
  protected FileModel() {}

  /**
   * Создаёт экземпляр класса {@link FileModel}.
   *
   * @param name Название файла.
   * @param dateCreated Дата создания файла.
   * @param format Формат файла.
   * @param urlFile Путь к файлу.
   * @param taskId Идентификатор задачи содержащей файл.
   */
  public FileModel(String name, LocalDateTime dateCreated,
                   Format format, String urlFile, int taskId) {
    this(Id.create(0).get(), name, dateCreated, format, urlFile, taskId);
  }

  /**
   * Создаёт экземпляр класса {@link FileModel}.
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
