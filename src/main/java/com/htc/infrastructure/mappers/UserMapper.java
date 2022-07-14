package com.htc.infrastructure.mappers;

import com.htc.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Представление пользователя для БД.
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserMapper {

    public UserMapper(String name, String password, String email, String avatar, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.role = role;
    }

    public UserMapper(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.role = user
                .getRole()
                .getRoleType()
                .name();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "password")
    public String password;

    @Column(name = "email")
    public String email;

    @Column(name = "avatar")
    public String avatar;

    @Column(name = "role")
    public String role;
}
