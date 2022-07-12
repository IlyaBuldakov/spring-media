package com.htc.infrastructure.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UserRepository;
import com.htc.infrastructure.models.UserModel;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория пользователей.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
  @Autowired
  Users users;

  @Override
  public CompletableFuture<Either<Failure, User>> create(
          User.Name name,
          User.Email email,
          User.Password password,
          User.Image image,
          User.Role role) {
    var user = new UserModel(
            1,
            name.getValue(),
            email.getValue(),
            password.getValue(),
            image.getValue(),
            role);

    return CompletableFuture.completedFuture(Either.right(users.save(user)));
  }

  @Override
  public CompletableFuture<Either<Failure, User>> update(
          Id id,
          User.Name name,
          User.Email email,
          User.Password password,
          User.Image image,
          User.Role role) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, User>> get(Id id) {
    return users.findOneById(id.getValue())
            .thenApply(Either::right);
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<User>>> getAll() {
    return null;
  }

  /**
   * ORM для доступа к данным пользователей в СУБД.
   */
  public interface Users extends JpaRepository<UserModel, Integer> {
    @Async
    CompletableFuture<UserModel> findOneById(int id);
  }
}