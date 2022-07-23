package com.htc.infrastructure.models.task;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.task.Status;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileName;
import com.htc.infrastructure.models.comment.CommentModel;
import com.htc.infrastructure.models.content.ContentModel;
import com.htc.infrastructure.models.file.FileModel;
import com.htc.infrastructure.models.user.UserModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;

/**
 * Представление сущности задачи в СУБД.
 */
@Entity
@Table(name = "TASKS")
@AllArgsConstructor
public class TaskModel implements Task {
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id")
  Long taskId;

  @Column(name = "name")
  String name;

  @Column(name = "type")
  String type;

  @Column(name = "description")
  String description;

  @ManyToOne
  @JoinColumn(name = "fileId")
  FileModel file;

  @ManyToOne
  @JoinColumn(name = "authorId")
  UserModel author;

  @ManyToOne
  @JoinColumn(name = "executorId")
  UserModel executor;

  @Column(name = "dateCreated")
  String dateCreated;

  @Column(name = "dateExpired")
  String dateExpired;

  @ManyToOne
  @JoinColumn(name = "contentId")
  ContentModel content;

  @ManyToOne
  @JoinColumn(name = "commentId")
  CommentModel comment;

  @Column(name = "status")
  String status;

  @Override
  public Id getId() {
    return Id.create(this.taskId).get();
  }

  @Override
  public FileName getName() {
    return FileName.create(this.name).get();
  }

  @Override
  public Type getType() {
    return Type.getFromName(this.type);
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public File getFile() {
    return this.file;
  }

  @Override
  public User getAuthor() {
    return this.author;
  }

  @Override
  public User getExecutor() {
    return this.executor;
  }

  @Override
  public DateCreated getDateCreated() {
    return DateCreated.create(this.dateCreated).get();
  }

  @Override
  public DateCreated getDateExpired() {
    return DateCreated.create(this.dateExpired).get();
  }

  @Override
  public Content getContent() {
    return this.content;
  }

  @Override
  public Comment getComment() {
    return this.comment;
  }

  @Override
  public Status getStatus() {
    return Status.getFromName(this.status);
  }

  protected TaskModel() {}
}