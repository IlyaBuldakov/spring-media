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
}

