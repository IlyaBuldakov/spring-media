package finalproject.application.services.impl;

import finalproject.application.services.UserService;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import finalproject.domain.repositories.UserRepository;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Async
  @Override
  public CompletableFuture<Either<Failure, User>> createNewUser(User user) {
    if (user.getId() < 0) {
      return CompletableFuture.completedFuture(Either.left(new Failure("Ничего не получилось")));
    }
    return CompletableFuture.completedFuture(Either.right(repository.save(user)));
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> editUser(User user) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> deleteUser(User user) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, User>> getUserById(int id) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, List<User>>> getAllUsers() {
    return CompletableFuture.completedFuture(Either.right((List<User>) repository.findAll()));

  }

  @Override
  public CompletableFuture<Either<Failure, List<User>>> getUsersByQuery(String query) {
    return null;
  }
}
