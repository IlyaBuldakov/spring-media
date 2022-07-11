package ru.kiryanovid.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.domain.entity.users.Role;

import javax.persistence.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private @Getter Role role;

    protected UserModel() {
    }

    public UserModel(Integer id) {
        this.id = id;

    }
}

