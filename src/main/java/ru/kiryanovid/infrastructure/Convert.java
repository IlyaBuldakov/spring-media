package ru.kiryanovid.infrastructure;

import ru.kiryanovid.domain.entity.users.User;
import ru.kiryanovid.infrastructure.models.UserModel;

/**
 * Служебный класс конвертации.
 */
public class Convert {
  /**
  * Конвертация модели пользователя в сущность пользователя.
  *
  * @param userModel Модель пользователя
  *
  * @return Пользователь
  */
  public static User convertUserModelToEntityUser(UserModel userModel) {
    return User.create(userModel.getId(),
                userModel.getName(),
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.getImage(),
                userModel.getRole()).get();
  }
}
