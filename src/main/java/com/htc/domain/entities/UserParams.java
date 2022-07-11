package com.htc.domain.entities;

import com.htc.application.dto.user.UserRequest;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author IlyaBuldakov
 */
@AllArgsConstructor
public class UserParams {
    public UserParams(UserRequest userRequest) {
        this.name = userRequest.getName();
        this.password = userRequest.getPassword();
        this.email = userRequest.getEmail();
        this.avatar = userRequest.getAvatar();
        this.role = userRequest.getRole();
    }

    public UserParams(User user) {
        this.id = String.valueOf(user.getId());
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.role = user.getRole();
    }

    public UserParams(String id, UserRequest userRequest) {
        this(userRequest);
        this.id = id;
    }

    private @Getter String id;

    private @Getter String name;

    private @Getter String password;

    private @Getter String email;

    private @Getter String avatar;

    private @Getter Role role;
}
