package com.htc.infrastructure.models.comment;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.infrastructure.models.user.UserModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;

/**
 * Представление сущности комментария в СУБД.
 */
@Entity
@Table(name = "comments")
@AllArgsConstructor
public class CommentModel implements Comment {
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id", unique = true, nullable = false)
  Long commentId;

  @Column(name = "dateCreated", nullable = false)
  String dateCreated;

  @ManyToOne
  @JoinColumn(name = "authorId", nullable = false)
  UserModel author;

  @Column(name = "message")
  String message;

  @Column(name = "contents")
  //TODO hibernate custom type [] // org.hibernate.mapping?
  String contents;

  @Override
  public Id getId() {
    return Id.create(this.commentId).get();
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
  public String getMessage() {
    return this.message;
  }

  @Override
  public int[] getContents() {
    return new int[0];
  }

  protected CommentModel() {}
}
