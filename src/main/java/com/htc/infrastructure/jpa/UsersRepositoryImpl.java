package com.htc.infrastructure.jpa;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.infrastructure.mappers.UserMapper;
import com.htc.infrastructure.repositories.UsersJpaRepository;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author IlyaBuldakov
 */
@Component
@AllArgsConstructor
public class UsersRepositoryImpl implements UsersRepository {
    UsersJpaRepository usersJpaRepository;

    @Override
    public CompletableFuture<Either<Failure, User>> create(String name,
                                                           String password,
                                                           String email,
                                                           String avatar,
                                                           Role role) {
        return CompletableFuture.completedFuture(
                        usersJpaRepository.save(
                                new UserMapper(name, password, email, avatar, role.getRoleType().name())))
                .thenApply(userMapper -> User.create(
                        userMapper.id + 1,
                        userMapper.name + "aa",
                        userMapper.password,
                        userMapper.email,
                        userMapper.avatar,
                        new Role(userMapper.role)));
    }

    @Override
    public CompletableFuture<Either<Failure, User>> getById(int id) {
        var user = usersJpaRepository.findById(id);
        return user.map(mapper -> CompletableFuture.completedFuture(mapper)
                .thenApply(
                        userMapper -> User.create(
                                userMapper.id,
                                userMapper.name,
                                userMapper.password,
                                userMapper.email,
                                userMapper.avatar,
                                new Role(userMapper.role)
                        ))).orElse(null);
    }

    @Override
    public CompletableFuture<Either<Failure, List<User>>> getAll() {
        return CompletableFuture.completedFuture(usersJpaRepository.findAll())
                .thenApply(list -> Either.right(
                        list.stream()
                                .map(userMapper -> User.create(
                                        userMapper.id,
                                        userMapper.name,
                                        userMapper.password,
                                        userMapper.email,
                                        userMapper.avatar,
                                        new Role(userMapper.role)
                                ).get()).toList()
                ));
    }

    @Override
    public CompletableFuture<Either<Failure, User>> update(int id,
                                                           String name,
                                                           String password,
                                                           String email,
                                                           String avatar,
                                                           Role role) {
        var user = User.create(id, name, password, email, avatar, role);
        return CompletableFuture.completedFuture(usersJpaRepository.save(new UserMapper(user.get())))
                .thenApply(ignoreUserMapper -> user);
    }

    @Override
    public CompletableFuture<Either<Failure, User>> deleteById(int id) {
        var user = getById(id);
        usersJpaRepository.deleteById(id);
        return user;
    }
}
