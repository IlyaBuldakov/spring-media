package finalproject.domain.entities.user;


import com.github.javafaker.Faker;
import finalproject.domain.entities.failures.Failure;
import finalproject.utils.Validators;
import io.vavr.control.Either;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;
import org.apache.commons.codec.binary.Base64;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Пользователь. Центр всей модели.
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

  static final String EMAIL_PATTERN = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
  static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})";

  static Faker faker = new Faker(new Locale("ru", "RU"));
  /**
   * Возвращает @return id Идентификатор пользователя.
   * Генерируется репозиторием
   */


  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  int id;

  /**
   * Электронная почта пользователя.
   *
   * @return id Электронная почта пользователя.
   */
  @Setter
  @Getter
  @Column
  private String email;

  /**
   * Возвращает @return name имя пользователя.
   */
  @Setter
  @Getter
  @Column
  private String name;

  /**
   * Изображение пользователя.
   *
   * @return id Изображение пользователя.
   */
  @Setter
  @Getter
  @Column
  private String avatar;


  /**
   * Пароль пользователя.
   *
   * @return Пароль пользователя.
   */
  @Setter
  @Getter
  @Column
  private String password;

  /**
   * Роль пользователя, см. {@link Role}.
   *
   * @return id Роль пользователя.
   */
  @Setter
  @Getter
  @Column
  private Role role;

  public User() {}
  public static Either<Failure, User> create(String email, String name, String avatar, String password, Role role) {
    List<String> problems = new ArrayList<>();
    User user = new User();
    if(Validators.patternValidate(email, EMAIL_PATTERN)) {
      user.email = email;}
    else {
      problems.add("email");
    }
    if(Validators.patternValidate(password, PASSWORD_PATTERN)) {
      user.password = password;}
    else {
      problems.add("password");
    }
    if(Validators.notNullString(name)) {
      user.name = name;}
    else {
      problems.add("name");
    }
    user.avatar = avatar;
    user.role = role;

    if(problems.size() == 0) {return Either.right(user);}
    else {
      Failure failure = new Failure("Invalid values");
      failure.setProblems(problems.toArray(new String[0]));
      return Either.left(failure);
    }
  }


   // Validate.isTrue(Base64.isBase64(avatar), "Invalid avatar");



  public static Either<Failure, User> createRandomFakeUser() {

    String email = faker.internet().emailAddress();
    String password = faker.lorem().characters(5, 17) + "1Aa";
    String name = faker.name().fullName();
    Role role = Role.values()[(int) (Math.random() * 3)];
    String avatar = faker.lorem().fixedString(64);
    return create(email, name, avatar, password, role);
  }

}
