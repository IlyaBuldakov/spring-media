package finalproject.domain.entities.user;


import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;
import org.apache.commons.codec.binary.Base64;
import org.springframework.data.annotation.Id;


import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Locale;


/**
 * Пользователь. Центр всей модели.
 */

public class User implements Serializable {

  static final String EMAIL_PATTERN = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
  static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})";

  static Faker faker = new Faker(new Locale("ru", "RU"));

  /**
   * Возвращает @return id Идентификатор пользователя.
   * Генерируется репозиторием
   */
  @Id
  @GeneratedValue
  @Getter
  @Setter
  private int id;

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
   // Validate.isTrue(Base64.isBase64(avatar), "Invalid avatar");
    this.email = email;
    this.name = name;
    this.avatar = avatar;
    this.password = password;
    this.role = role;
  }

  public static User createRandomFakeUser() {

    String email = faker.internet().emailAddress();
    String password = faker.lorem().characters(5, 17) + "1Aa";
    String name = faker.name().fullName();
    Role role = Role.values()[(int) (Math.random() * 3)];
    String avatar = faker.lorem().fixedString(64);
    return new User(email, name, avatar, password, role);
  }

}
