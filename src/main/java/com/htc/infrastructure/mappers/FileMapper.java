package com.htc.infrastructure.mappers;

import com.htc.domain.entities.file.File;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Представление сущности файла для БД.
 */
@Entity
@Table(name = "files")
@NoArgsConstructor
public class FileMapper implements File {

  /**
   * Конструктор без идентификатора.
   *
   * @param name        Имя файла.
   * @param dateCreated Дата создания файла.
   * @param format      Формат файла.
   * @param url         URL файла.
   * @param taskId      Идентификатор задачи.
   */
  public FileMapper(String name, LocalDate dateCreated, FileFormat format, String url, int taskId) {
    this.name = name;
    this.dateCreated = dateCreated;
    this.format = format;
    this.url = url;
    this.taskId = taskId;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true)
  public @Getter Integer id;

  private @Getter String name;

  private @Getter LocalDate dateCreated;

  @Enumerated(EnumType.STRING)
  private @Getter FileFormat format;

  private @Getter String url;

  @Column(name = "task_id")
  private @Getter Integer taskId;
}
