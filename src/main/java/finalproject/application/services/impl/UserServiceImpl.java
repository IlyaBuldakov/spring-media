package finalproject.application.services.impl;

import finalproject.application.services.UserService;
import finalproject.domain.entities.failures.BadRequest;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.failures.Messages;
import finalproject.domain.entities.failures.NotFound;
import finalproject.domain.entities.user.User;
import finalproject.infrastructure.repositories.UserRepository;
import io.vavr.control.Either;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * Реализация сервиса по работе с пользователями.
 *
 */
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
      return CompletableFuture.completedFuture(Either.left(
              new BadRequest(Messages.USERS_EMAIL_IS_ALREADY_EXISTS, problems)));
    }
    return CompletableFuture.completedFuture(Either.right(repository.save(user)));
  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, User>> editUser(User user, int id) {
    List<String> problems = new ArrayList<>();
    if (id < 1) {
      problems.add("id");
      return CompletableFuture.completedFuture(Either.left(
              new BadRequest(Messages.INVALID_VALUES, problems)));
    }
    if (!repository.existsById(id)) {
      return CompletableFuture.completedFuture(Either.left(
              new NotFound(Messages.USER_NOT_FOUND)));
    }
    if (anotherUserHasUpdatedEmail(user.getEmail(), id)) {
      problems.add("email");
      return CompletableFuture.completedFuture(Either.left(
              new BadRequest(Messages.USERS_EMAIL_IS_ALREADY_EXISTS, problems)));
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
    if (id <= 1) {
      problems.add("id");
      return CompletableFuture.completedFuture(Either.left(
              new BadRequest(Messages.INVALID_VALUES, problems)));
    }
    return CompletableFuture.completedFuture(Either.left(
            new NotFound(Messages.USER_NOT_FOUND)));
  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, User>> getUserById(int id) {
    List<String> problems = new ArrayList<>();
    if (id < 1) {
      problems.add("id");
      return CompletableFuture.completedFuture(Either.left(
              new BadRequest(Messages.INVALID_VALUES, problems)));
    }
    Optional<User> user = repository.findById(id);
    if (user.isPresent()) {
      return CompletableFuture.completedFuture(Either.right(user.get()));
    }
    return CompletableFuture.completedFuture(
            Either.left(new NotFound(Messages.USER_NOT_FOUND)));

  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, List<User>>> getAllUsers() {
    return CompletableFuture.completedFuture(Either.right(repository.findAll()));

  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, User>> getUserByEmail(String email) {
    if (isEmailExists(email)) {
      return CompletableFuture.completedFuture(Either.right(
            repository.findOneByEmail(email).get()));
    } else {
      return CompletableFuture.completedFuture(Either.left(
            new NotFound(Messages.USER_NOT_FOUND)));
    }



  }

  public boolean isEmailExists(String email) {
    return repository.findOneByEmail(email).isPresent();
  }

  /**Проверка, есть ли уже у пользователей в репозитории
   *  такой же email при изменении данных пользователя.
   *
   * @param email новый email пользователя
   * @param id id текущего пользователя
   * @return boolean
   */
  public boolean anotherUserHasUpdatedEmail(String email, int id) {
    if (isEmailExists(email)) {
      return repository.findOneByEmail(email).get().getId() == id;
    }
    return false;
  }


}
