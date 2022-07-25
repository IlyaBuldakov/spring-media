package ru.kiryanovid.infrastructure.repositories;

import io.vavr.control.Either;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.users.User;
import ru.kiryanovid.domain.repositories.UserRepositories;
import ru.kiryanovid.infrastructure.models.UserModel;

/**
 * Реализация репозитория пользователей.
 */

@Repository
public class UserRepositoriesImpl implements UserRepositories {

  private final Users users;

  public UserRepositoriesImpl(Users users) {
    this.users = users;
  }

  @Override
  public CompletableFuture<Either<Failure, User>> create(User user) {
    var userModel = new UserModel(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getImage(),
                user.getRole()
        );
    users.save(userModel);
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, User>> update(User user) {
    var userModel = new UserModel(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getImage(),
                user.getRole()
        );
    users.save(userModel);
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Integer id) {
    users.deleteById(id);
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, User>> get(Integer id) {
    var userModel = users.findById(id).get();
    var user = User.create(userModel.getId(),
                userModel.getName(),
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.getImage(),
                userModel.getRole()).get();
    return CompletableFuture.completedFuture(Either.right(user));
  }

  @Override
  public CompletableFuture<Either<Failure, Integer>> getUserByEmail(String email) {
    var countOfFound = users.countAllByEmail(email).get();
    return CompletableFuture.completedFuture(Either.right(countOfFound));
  }

  @Override
  public CompletableFuture<Either<Failure, Iterable<User>>> getAll() {
    var userList = users.findAll().stream().map(userModel -> User.create(userModel.getId(),
                userModel.getName(),
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.getImage(),
                userModel.getRole()
        ).get()).collect(Collectors.toList());
    return CompletableFuture.completedFuture(Either.right(userList));
  }

  /**
   * ORM для доступа к данным пользователей в СУБД.
   */
  public interface Users extends JpaRepository<UserModel, Integer> {
    Optional<Integer> countAllByEmail(String email);
  }
}
