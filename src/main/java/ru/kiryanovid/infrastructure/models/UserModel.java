package ru.kiryanovid.infrastructure.models;

import lombok.Getter;
import ru.kiryanovid.domain.entity.users.Role;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserModel {
    /**
     * Пользователь.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter Integer id;

    private @Getter String name;

    private @Getter String email;

    private @Getter String password;

    private @Getter String image;

    private @Getter Role role;

    protected UserModel() {
    }

   /* public UserModel(Integer id, String name, String email, String password, String image, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
    }*/
    public UserModel(Integer id) {
        this.id = id;

    }
}

