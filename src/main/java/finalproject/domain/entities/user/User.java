package finalproject.domain.entities.user;



import finalproject.domain.entities.failures.Failure;
import finalproject.utils.Validators;
import io.vavr.control.Either;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;



/**
 * Пользователь. Центр всей модели.
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

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
    User user = new User();
    Validators validators = new Validators();
    validators.validateEmail(email);
    validators.validatePassword(password);
    validators.validateNonNullString(name, "name");
    validators.validateNotNull(role, "role");
    if (validators.problems.size() == 0) {
      user.name = name;
      user.email = email;
      user.password = password;
      user.avatar = avatar;
      user.role = role;
      return Either.right(user);
    }
      return Either.left(new Failure(Failure.Messages.INVALID_VALUES, validators.problems));
    }
  }


   // Validate.isTrue(Base64.isBase64(avatar), "Invalid avatar");



//  public static Either<Failure, User> createRandomFakeUser() {
//
//    String email = faker.internet().emailAddress();
//    String password = faker.lorem().characters(5, 17) + "1Aa";
//    String name = faker.name().fullName();
//    Role role = Role.values()[(int) (Math.random() * 3)];
//    String avatar = faker.lorem().fixedString(64);
//    return create(email, name, avatar, password, role);
//  }


