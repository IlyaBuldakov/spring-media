package com.htc.infrastructure.mappers;

import com.htc.domain.entities.comment.Comment;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

  /**
   * Конструктор без идентификатора.
   *
   * @param taskId      Идентификатор задачи.
   * @param dateCreated Дата создания комментария.
   * @param authorId    Идентификатор автора.
   * @param message     Сообщение комментария.
   */
  public CommentMapper(int taskId, LocalDate dateCreated, int authorId, String message) {
    this.taskId = taskId;
    this.dateCreated = dateCreated;
    this.authorId = authorId;
    this.message = message;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true)
  public @Getter Integer id;

  private @Getter LocalDate dateCreated;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "author_id", insertable = false, updatable = false)
  private @Getter UserMapper author;

  @Column(name = "author_id")
  private @Getter int authorId;

  private @Getter String message;

  @Column(name = "task_id")
  private @Getter int taskId;
}
