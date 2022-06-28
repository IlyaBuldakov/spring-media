package finalproject.application.services.impl;

import finalproject.application.services.UserService;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import finalproject.domain.repositories.UserRepository;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Async
  @Override
  public CompletableFuture<Either<Failure, User>> createNewUser(User user) {
    return CompletableFuture.completedFuture(Either.right(repository.save(user)));
  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, User>> editUser(User user) {
    return CompletableFuture.completedFuture(Either.right(repository.save(user)));
  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, Void>> deleteUserById(int id) {
    repository.deleteById(id);
    return CompletableFuture.completedFuture(Either.right(null));
  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, User>> getUserById(int id) {
    Optional<User> user = repository.findById(id);
    if (user.isPresent()) {
    return CompletableFuture.completedFuture(Either.right((user.get())));
    }
    return CompletableFuture.completedFuture(Either.left(new Failure("Ничего не получилось")));

  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, List<User>>> getAllUsers() {
    return CompletableFuture.completedFuture(Either.right((List<User>) repository.findAll()));

  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, List<User>>> getUsersByQuery(String query) {
    return null;
  }
}
