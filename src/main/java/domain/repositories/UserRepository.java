package domain.repositories;

import domain.entities.User;

/**
 * Репозиторий пользователя.
 */
public interface UserRepository {
  /**
   * Добавление нового пользователя.
   *
   * @param user - новый пользователь
   */
  void add(User user);

  /**
   * Получение пользователя.
   *
   * @param id - идентификатор пользователя
   *
   * @return user - пользователь, подробнее {@link User}
   */
  User get(int id);

  /**
   * Обновление пользователя.
   *
   * @param user - пользователь, подробнее {@link User}
   */
  void update(User user);

  /**
   * Удаление пользователя.
   *
   * @param id - идентификатор пользователя
   */
  void delete(int id);
}
