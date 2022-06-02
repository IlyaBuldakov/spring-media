package finalproject.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Роль пользователя.
 */
@AllArgsConstructor
public class RoleDto {
  /**
   * Виды ролей пользователя.
   */
  public enum RoleType {
    /**
     * Администратор.
     */
    ADMIN,
    /**
     * Менеджер.
     */
    MANAGER,
    /**
     * Контент-мейкер.
     */
    CONTENT_MAKER
  }

  /**
   * Идентификатор роли.
   *
   * @return id Идентификатор роли.
   */
  private @Getter int id;

  /**
   * Название роли.
   *
   * @return name Название роли.
   */
  private @Getter RoleType name;
}
