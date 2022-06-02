package finalproject.domain.entities.user;


import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;
import org.apache.commons.codec.binary.Base64;
import org.springframework.data.annotation.Id;


import javax.persistence.GeneratedValue;


/**
 * Пользователь. Центр всей модели.
 */

public class User {

  static final String EMAIL_PATTERN = "^[a-zA-Z\\d_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z\\d.-]+$";
  static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})";



  /**
   * Возвращает @return id Идентификатор пользователя.
   * Генерируется репозиторием
   */
  @Id
  @GeneratedValue
  private @Getter int id;

  /**
   * Электронная почта пользователя.
   *
   * @return id Электронная почта пользователя.
   */
  @Setter
  @Getter
  private String email;

  /**
   * Возвращает @return name имя пользователя.
   */
  @Setter
  @Getter
  private String name;

  /**
   * Изображение пользователя.
   *
   * @return id Изображение пользователя.
   */
  @Setter
  @Getter
  private String avatar;


  /**
   * Пароль пользователя.
   *
   * @return Пароль пользователя.
   */
  @Setter
  @Getter
  private String password;

  /**
   * Роль пользователя, см. {@link Role}.
   *
   * @return id Роль пользователя.
   */
  @Setter
  @Getter
  private Role role;

  public User(String email, String name, String avatar, String password, Role role) {
    Validate.matchesPattern(email, EMAIL_PATTERN, "invalid email");
    Validate.matchesPattern(password, PASSWORD_PATTERN, "invalid password");
    Validate.isTrue(name.length() > 0, "Invalid name");
    Validate.isTrue(Base64.isBase64(avatar), "Invalid avatar");
    this.email = email;
    this.name = name;
    this.avatar = avatar;
    this.password = password;
    this.role = role;
  }
}
