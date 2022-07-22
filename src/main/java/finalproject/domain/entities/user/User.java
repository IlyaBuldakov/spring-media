package finalproject.domain.entities.user;



import finalproject.domain.entities.failures.Failure;
import finalproject.utils.Validators;
import io.vavr.control.Either;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;



/**
 * Пользователь. Центр всей модели.
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  int id;

  /**
   * Электронная почта пользователя.
   *
   * @return email Электронная почта пользователя.
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

  /**
   * Конструктор Пользователя.
   *
   * @param email email пользователя
   * @param name Имя пользователя
   * @param avatar Изображение пользователя
   * @param password Пароль пользователя
   * @param role Роль пользователя
   * @return User Пользователя
   */
  public static Either<Failure, User> create(String email, String name,
                                             String avatar, String password, Role role) {
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





