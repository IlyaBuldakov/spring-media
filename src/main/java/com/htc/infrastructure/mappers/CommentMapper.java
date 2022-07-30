package com.htc.infrastructure.mappers;

import com.htc.domain.entities.comment.Comment;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Представление сущности комментария для БД.
 */
@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
public class CommentMapper implements Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true)
  public @Getter Integer id;

  private @Getter LocalDate dateCreated;

  @OneToOne(fetch = FetchType.EAGER)
  private @Getter UserMapper author;

  private @Getter String message;
}
