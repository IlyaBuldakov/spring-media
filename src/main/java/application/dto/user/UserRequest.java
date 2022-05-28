package application.dto.user;

import application.dto.DataTransferObject;
import domain.entities.user.Role;
import domain.entities.user.User;
import lombok.Getter;

/**
 * Представление сущности пользователя (ответ)
 *
 * @author IlyaBuldakov
 */
public class UserRequest extends DataTransferObject<User> {

    public UserRequest(User user) {
        super(user);

        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.role = user.getRole();
    }

    /**
     * Имя пользователя
     *
     * @return name Имя пользователя
     */
    private final @Getter String name;

    /**
     * Пароль пользователя
     *
     * {@link User#getPassword() Требования к паролю}
     *
     * @return password Пароль пользователя
     */
    private final @Getter String password;

    /**
     * E-mail пользователя
     *
     * @return email E-mail пользователя
     */
    private final @Getter String email;

    /**
     * Аватар пользователя
     *
     * @return avatar Аватар пользователя
     */
    private final @Getter byte[] avatar;

    /**
     * Роль пользователя {@link Role.RoleType}
     *
     * @return role Роль пользователя
     */
    private final @Getter Role role;
}
