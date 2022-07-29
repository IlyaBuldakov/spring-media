package com.htc.infrastructure.models.task;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.task.Status;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.EntityName;
import com.htc.domain.entities.utility.parameters.Id;
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
@Table(name = "tasks")
@AllArgsConstructor
public class TaskModel implements Task {
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id", unique = true, nullable = false)
  Long taskId;

  @Column(nullable = false)
  String name;

  @Column(nullable = false)
  String type;

  String description;

  @ManyToOne
  @JoinColumn(nullable = false)
  FileModel file;

  @ManyToOne
  @JoinColumn(name = "authorId", nullable = false)
  UserModel author;

  @ManyToOne
  @JoinColumn(name = "executorId", nullable = false)
  UserModel executor;

  @Column(nullable = false)
  String dateCreated;

  @Column(nullable = false)
  String dateExpired;

  @ManyToOne
  @JoinColumn(name = "contentId", nullable = false)
  ContentModel content;

  @ManyToOne
  @JoinColumn(name = "commentId", nullable = false)
  CommentModel comment;

  @Column(nullable = false)
  String status;

  @Override
  public Id getId() {
    return Id.create(this.taskId).get();
  }

  @Override
  public EntityName getName() {
    return EntityName.create(this.name).get();
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
