package com.htc.infrastructure.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.infrastructure.mappers.UserMapper;
import com.htc.infrastructure.jpa.UsersJpaRepository;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация репозитория пользователей.
 */
@Component
@AllArgsConstructor
public class UsersRepositoryImpl implements UsersRepository {

    UsersJpaRepository usersJpaRepository;

    /**
     * Создание пользователя.
     *
     * @param name     Имя пользователя.
     * @param password Пароль пользователя.
     * @param email    E-mail пользователя.
     * @param avatar   Аватар пользователя.
     * @param role     Роль пользователя.
     * @return Пользователь.
     */
    @Override
    public CompletableFuture<Either<Failure, Void>> create(String name,
                                                           String password,
                                                           String email,
                                                           String avatar,
                                                           Role role) {
        usersJpaRepository.save(new UserMapper(name, password, email, avatar, role));
        return CompletableFuture.completedFuture(Either.right(null));
    }

    /**
     * Получение пользователя по идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return Пользователь.
     */
    @Override
    public CompletableFuture<Either<Failure, User>> getById(int id) {
        var user = usersJpaRepository.findById(id);
        if (user.isPresent()) {
            return CompletableFuture.completedFuture(Either.right(user.get()));
        }
        return CompletableFuture.completedFuture(Either.left(NotFound.USER));
    }

    /**
     * Получение списка всех пользователей.
     *
     * @return Список пользователей.
     */
    @Override
    public CompletableFuture<Either<Failure, List<User>>> getAll() {
        return CompletableFuture.completedFuture(Either.right(
                new ArrayList<>(usersJpaRepository.findAll())));
    }

    /**
     * Обновление пользователя.
     *
     * @param id       Идентификатор пользователя.
     * @param name     Имя пользователя.
     * @param password Пароль пользователя.
     * @param email    E-mail пользователя.
     * @param avatar   Аватар пользователя.
     * @param role     Роль пользователя.
     * @return Обновлённый пользователь.
     */
    @Override
    public CompletableFuture<Either<Failure, Void>> update(int id,
                                                           String name,
                                                           String password,
                                                           String email,
                                                           String avatar,
                                                           Role role) {
        usersJpaRepository.save(new UserMapper(id, name, password, email, avatar, role));
        return CompletableFuture.completedFuture(Either.right(null));
    }

    /**
     * Удаление пользователя.
     *
     * @param id Идентификатор пользователя.
     * @return Удалённый пользователь.
     */
    @Override
    public CompletableFuture<Either<Failure, Void>> deleteById(int id) {
        try {
            usersJpaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            return CompletableFuture.completedFuture(Either.left(NotFound.USER));
        }
        return CompletableFuture.completedFuture(Either.right(null));
    }
}
