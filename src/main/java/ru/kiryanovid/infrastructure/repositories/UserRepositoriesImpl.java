package ru.kiryanovid.infrastructure.repositories;

import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.users.User;
import ru.kiryanovid.domain.repositories.UserRepositories;
import ru.kiryanovid.infrastructure.models.UserModel;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Repository
public class UserRepositoriesImpl implements UserRepositories {

    @Autowired
    Users users;

    @Override
    public CompletableFuture<Either<Failure, User>> create(User user) {
        return null;
    }

    @Override
    public Future<Either<Failure, User>> update(User user) {
        return null;
    }

    @Override
    public Future<Either<Failure, Void>> delete(Integer id) {
        return null;
    }

    @Override
    public CompletableFuture<Either<Failure, User>> get(Integer id) {
        var userModel = users.findById(id).orElseThrow();
        var user = User.create(userModel.getId(),
                userModel.getName(),
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.getImage(),
                userModel.getRole()).get();
        return CompletableFuture.completedFuture(Either.right(user));
    }

    @Override
    public CompletableFuture<Either<Failure, Iterable<User>>> getAll() {
        return null;
    }

    public interface Users extends JpaRepository<UserModel, Integer>{}
}
