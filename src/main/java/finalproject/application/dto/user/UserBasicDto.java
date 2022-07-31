package finalproject.application.dto.user;

import finalproject.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Пользователь - основные данные.
 */

@NoArgsConstructor
@AllArgsConstructor
public class UserBasicDto {
  /**
   * Возвращает @return id Идентификатор пользователя.
   */
  private @Getter int id;

  /**
   * Возвращает @return name имя пользователя.
   */
  private @Getter String name;

  public UserBasicDto(User user) {
    this.id = user.getId();
    this.name = user.getName();
  }

}
