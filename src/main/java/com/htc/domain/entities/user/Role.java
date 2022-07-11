package com.htc.domain.entities.user;

import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Класс, описывающий роль пользователя {@link RoleType}
 *
 * @author IlyaBuldakov
 */
@AllArgsConstructor
public class Role {

    /**
     * Конструктор, превращающий строку из запроса в Role
     *
     * @param input Строка
     */
    public Role(String input) {
        RoleType type = validateRoleTypeInput(input).get();
        this.id = type.ordinal() + 1;
        this.roleType = type;
    }

    /**
     * Метод валидации строки из запроса
     * на соответствие RoleType'у
     *
     * @param input Строка для валидации
     * @return Failure или RoleType
     */
    public static Either<Failure, RoleType> validateRoleTypeInput(String input) {
        return Either.right(RoleType.valueOf(input.toUpperCase()));
    }

    /**
     * Тип роли
     */
    public enum RoleType {
        /**
         * Администратор
         */
        ADMIN,

        /**
         * Менеджер
         */
        MANAGER,

        /**
         * Контент-мейкер
         */
        CONTENT_MAKER
    }

    /**
     * Идентификатор роли.
     *
     * @return id Идентификатор роли.
     */
    private @Getter int id;

    /**
     * Тип роли {@link RoleType}
     *
     * @return roleType Тип роли
     */
    private @Getter RoleType roleType;
}
