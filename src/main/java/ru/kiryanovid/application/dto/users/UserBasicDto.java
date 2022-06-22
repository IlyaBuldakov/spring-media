package ru.kiryanovid.application.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.entity.users.User;

/**
 *
 */
@AllArgsConstructor
public class UserBasicDto {
    /**
     * Идентификатор пользователя
     *
     */
    @Getter private Integer id;

    /**
     * Имя пользователя
     */
    @Getter private String name;

    public UserBasicDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public static UserBasicDto mapToDto(User user){
        return new UserBasicDto(user);
    }
}
