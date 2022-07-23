package com.htc.infrastructure.models.content;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.file.Format;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileName;
import com.htc.domain.entities.utility.parameters.file.FileUrlPath;
import com.htc.infrastructure.models.file.FileModel;
import com.htc.infrastructure.models.task.TaskModel;
import com.htc.infrastructure.models.user.UserModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;

/**
 * Представление сущности контента в СУБД.
 */
@Entity
@Table(name = "CONTENTS")
@AllArgsConstructor
public class ContentModel implements Content {
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id")
  Long contentId;

  @Column(name = "type")
  String type;

  @Column(name = "name")
  String name;

  @Column(name = "dateCreated")
  String dateCreated;

  @ManyToOne
  @JoinColumn(name = "authorId")
  UserModel author;

  @Column(name = "format")
  String format;

  @Column(name = "previewPath")
  String previewPath;

  @ManyToOne
  @JoinColumn(name = "fileId")
  FileModel file;

  @ManyToOne
  @JoinColumn(name = "taskId")
  TaskModel task;

  @Override
  public Id getId() {
    return Id.create(this.contentId).get();
  }

  @Override
  public Type getType() {
    return Type.getFromName(this.type);
  }

  @Override
  public FileName getName() {
    return FileName.create(this.name).get();
  }

  @Override
  public DateCreated getDateCreated() {
    return DateCreated.create(this.dateCreated).get();
  }

  @Override
  public User getAuthor() {
    return this.author;
  }

  @Override
  public Format getFormat() {
    return Format.getFromName(this.format);
  }

  @Override
  public FileUrlPath getPreviewPath() {
    return FileUrlPath.create(this.previewPath).get();
  }

  @Override
  public File getFile() {
    return this.file;
  }

  @Override
  public Task getTask() {
    return this.task;
  }

  protected ContentModel() {}
}