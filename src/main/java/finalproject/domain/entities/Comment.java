package finalproject.domain.entities;

import finalproject.domain.entities.user.User;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Сущность - комментарий.
 */
public class Comment {

  private @Getter int id;

  private @Getter int task;

  private @Getter LocalDateTime date;

  private @Getter User user;

  private @Getter String message;
}
