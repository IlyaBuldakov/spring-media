package com.htc.infrastructure.models;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.files.File;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
  private @Getter LocalDateTime dateCreated;

  /**
   * Формат файла.
   */
  @NotNull
  private @Getter File.Format format;

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
  public @Getter TaskModel task;

  @Override
  public Id getId() {
    return Id.create(fileId).get();
  }

  @Override
  public Name getName() {
    return File.Name.create(name).get();
  }

  @Override
  public Url getUrl() {
    return Url.create(url).get();
  }

  protected FileModel() {
  }
}
