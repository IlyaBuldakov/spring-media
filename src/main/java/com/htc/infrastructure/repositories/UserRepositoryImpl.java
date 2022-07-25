package com.htc.infrastructure.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.User;
import com.htc.domain.repositories.UserRepository;
import com.htc.infrastructure.models.UserModel;
import io.vavr.control.Either;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория пользователей.
 */
@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  Users users;

  @Override
  public CompletableFuture<Either<Failure, User>> create(
          User.Name name,
          User.Email email,
          User.Password password,
          User.Image image,
          User.Role role) {
    var user = new UserModel(name, email, password, image, role);
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
    var user = new UserModel(id, name, email, password, image, role);
    return CompletableFuture.completedFuture(Either.right(users.save(user)));
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    users.deleteById(id.getValue());
    return CompletableFuture.completedFuture(Either.right(null));
  }

  @Override
  public CompletableFuture<Either<Failure, User>> get(Id id) {
    var user = users.findById(id.getValue());
    return user.isPresent()
            ? CompletableFuture.completedFuture(Either.right(user.get()))
            : CompletableFuture.completedFuture(Either.left(NotFound.DEFAULT_MESSAGE));
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<User>>> getAll() {
    return CompletableFuture.completedFuture(Either.right(new ArrayList<>(users.findAll())));
  }

  /**
   * ORM для доступа к данным пользователей в СУБД.
   */
  public interface Users extends JpaRepository<UserModel, Integer> {
  }
}