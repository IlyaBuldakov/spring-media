package com.htc.infrastructure.repositories;

import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.AlreadyExists;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.repositories.UserRepository;
import com.htc.infrastructure.models.UserModel;
import com.htc.utility.Results;
import io.vavr.control.Either;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    var user = new UserModel(name, email, password, image, role);
    return Results.succeed(users.save(user));
  }

  @Override
  public CompletableFuture<Either<Failure, User>> createIfEmailNotExists(
      User.Name name,
      User.Email email,
      User.Password password,
      User.Image image,
      User.Role role) {
    var count = this.users.countByEmail(email.getValue());
    return count == 0
      ? this.create(name, email, password, image, role)
      : Results.fail(AlreadyExists.USER_WITH_EMAIL);
  }

  @Override
  public CompletableFuture<Either<Failure, User>> update(
      Id id,
      User.Name name,
      User.Email email,
      User.Password password,
      User.Image image,
      User.Role role) {
    var user = new UserModel(id, name, email, password, image, role);
    return Results.succeed(users.save(user));
  }

  @Override
  @Transactional
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    users.deleteById(id.getValue());
    return Results.succeed(null);
  }

  @Override
  public CompletableFuture<Either<Failure, User>> get(Id id) {
    var user = users.findById(id.getValue());
    return user.isPresent()
        ? Results.succeed(user.get())
        : Results.fail(NotFound.DEFAULT_MESSAGE);
  }

  @Override
  public CompletableFuture<Either<Failure, User>> get(User.Email email) {
    var user = users.findOneByEmail(email.getValue());
    return user.isPresent()
        ? Results.succeed(user.get())
        : Results.fail(NotFound.USER_WITH_EMAIL);
  }

  public CompletableFuture<Either<Failure, Collection<User>>> getAll() {

    return Results.succeed(new ArrayList<>(users.findAll()));
  }

  /**
   * ORM для доступа к данным пользователей в СУБД.
   */
  public interface Users extends JpaRepository<UserModel, Integer> {
    Optional<User> findOneByEmail(String email);

    int countByEmail(String email);
  }
}
