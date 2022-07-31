package finalproject.application.dto.comment;

import finalproject.application.dto.user.UserBasicDto;
import finalproject.domain.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Комментарий.
 */
@AllArgsConstructor
public class CommentDto {

  /**
   * Возвращает @return id идентификатор коммментария.
   */
  private @Getter int id;

  /**
   * Возвращает @return LocalDateTime date дату и время создания комментария.
   */
  private @Getter String date;

  /**
   * Возвращает @return UserBasicDto user автора комментария.
   */
  private @Getter UserBasicDto user;

  /**
   * Возвращает @return текст комментария.
   */
  private @Getter String message;

  /**
   * Конструктор Dto комментария.
   *
   * @param comment комментарий
   */
  public CommentDto(Comment comment) {
    this.id = comment.getId();
    this.date = comment.getDate().toString();
    this.user = new UserBasicDto(comment.getUser());
    this.message = comment.getMessage();
  }


}
