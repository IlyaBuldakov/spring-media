package finalproject.application.services.impl;
import finalproject.application.services.UserService;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import finalproject.domain.repositories.UserRepository;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;



@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Async
  @Override
  public CompletableFuture<Either<Failure, User>> createNewUser(User user) {
    List<String> problems = new ArrayList<>();
    if (isEmailExists(user.getEmail())) {
      problems.add("email");
      return CompletableFuture.completedFuture(Either.left(new Failure(Failure.Messages.USERS_EMAIL_IS_ALREADY_EXISTS, problems)));
    }
    return CompletableFuture.completedFuture(Either.right(repository.save(user)));
  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, User>> editUser(User user, int id) {
    List<String> problems = new ArrayList<>();
    if (id <= 0) {
      problems.add("id");
      return CompletableFuture.completedFuture(Either.left(new Failure(Failure.Messages.INVALID_VALUES, problems)));
    }
    if (isEmailExists(user.getEmail())) {
      problems.add("email");
      return CompletableFuture.completedFuture(Either.left(new Failure(Failure.Messages.USERS_EMAIL_IS_ALREADY_EXISTS, problems)));
    }
    if (!repository.existsById(id)) {
      return CompletableFuture.completedFuture(Either.left(new Failure(Failure.Messages.USER_NOT_FOUND)));
    }
    return CompletableFuture.completedFuture(Either.right(repository.save(user)));
  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, Void>> deleteUserById(int id) {
    List<String> problems = new ArrayList<>();
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return CompletableFuture.completedFuture(Either.right(null));
    }
    if (id <= 0) {
      problems.add("id");
      return CompletableFuture.completedFuture(Either.left(new Failure(Failure.Messages.INVALID_VALUES, problems)));
    }
    return CompletableFuture.completedFuture(Either.left(new Failure(Failure.Messages.USER_NOT_FOUND)));
  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, User>> getUserById(int id) {

    Optional<User> user = repository.findById(id);
    if (user.isPresent()) {
    return CompletableFuture.completedFuture(Either.right(user.get()));
    }
    return CompletableFuture.completedFuture(Either.left(new Failure(Failure.Messages.USER_NOT_FOUND)));

  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, List<User>>> getAllUsers() {
    return CompletableFuture.completedFuture(Either.right((List<User>) repository.findAll()));

  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, User>> getUserByEmail(String email) {
    for (User user : repository.findAll()) {
      if (user.getEmail().equals(email)) {
        return CompletableFuture.completedFuture(Either.right(user));
      }
    }
      return CompletableFuture.completedFuture(Either.left(new Failure(Failure.Messages.USER_NOT_FOUND)));
  }

  public boolean isEmailExists (String email) {
    for (User user : repository.findAll()) {
      if (user.getEmail().equals(email)) {
        return true;
      }
    }
    return false;
  }



}
