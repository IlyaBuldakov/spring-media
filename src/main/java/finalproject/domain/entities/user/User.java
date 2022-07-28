package finalproject.domain.entities.user;




import com.fasterxml.jackson.annotation.JsonIgnore;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.task.Task;
import finalproject.utils.validators.Validators;
import io.vavr.control.Either;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;



/**
 * Пользователь.
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  /**
   * Электронная почта пользователя.
   *
   * @return email Электронная почта пользователя.
   */
  @Setter
  @Getter
  @Column
  private String email;

  /**Имя пользователя.
   *
   * @return name name имя пользователя.
   */
  @Setter
  @Getter
  @Column
  private String name;

  /**
   * Изображение пользователя.
   *
   * @return avatar Изображение пользователя.
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

  @JsonIgnore
  @Getter
  @Setter
  @OneToMany(mappedBy = "author")
  private List<Task> tasksAsAuthor;

  @JsonIgnore
  @Getter
  @Setter
  @OneToMany(mappedBy = "contentMaker")
  private List<Task> tasksAsContentMaker;

  public Integer getId(){return id;}
  public void setId(Integer id){this.id = id;}

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
    if (validators.getProblems().size() == 0) {
      user.name = name;
      user.email = email;
      user.password = password;
      user.avatar = avatar;
      user.role = role;
      return Either.right(user);
    }
    return Either.left(new Failure(Failure.Messages.INVALID_VALUES, validators.getProblems()));
  }
}





