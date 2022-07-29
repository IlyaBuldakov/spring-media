package finalproject.application.services.impl;

import finalproject.application.services.AuthService;
import finalproject.application.services.UserService;
import finalproject.domain.entities.failures.*;
import finalproject.domain.entities.user.Role;
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

  private final UserRepository userRepository;
  private final AuthService authService;


  @Async
  @Override
  public CompletableFuture<Either<Failure, User>> createNewUser(User user) {
    List<String> problems = new ArrayList<>();
    if (isEmailExists(user.getEmail())) {
      problems.add("email");
      return CompletableFuture.completedFuture(Either.left(
              new BadRequest(Messages.USERS_EMAIL_IS_ALREADY_EXISTS, problems)));
    }
    return CompletableFuture.completedFuture(Either.right(userRepository.save(user)));
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
    if (!userRepository.existsById(id)) {
      return CompletableFuture.completedFuture(Either.left(
              new NotFound(Messages.USER_NOT_FOUND)));
    }
    if (anotherUserHasUpdatedEmail(user.getEmail(), id)) {
      problems.add("email");
      return CompletableFuture.completedFuture(Either.left(
              new BadRequest(Messages.USERS_EMAIL_IS_ALREADY_EXISTS, problems)));
    }
    return CompletableFuture.completedFuture(Either.right(userRepository.save(user)));

  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, Void>> deleteUserById(int id) {
    List<String> problems = new ArrayList<>();
    if (userRepository.existsById(id)) {
      userRepository.deleteById(id);
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
    Optional<User> user = userRepository.findById(id);
    User authorizedUser = userRepository.findById(authService.getId()).get();
    if (user.isPresent()) {
      if (authorizedUser.getRole() == Role.ADMIN || user.get().getRole() == Role.CONTENT_MAKER) {
      return CompletableFuture.completedFuture(Either.right(user.get())); }
      else {
        return CompletableFuture.completedFuture(
                Either.left(new NotAuthorized(Messages.NOT_ENOUGH_AUTHORITY)));
      }
    }
    return CompletableFuture.completedFuture(
            Either.left(new NotFound(Messages.USER_NOT_FOUND)));

  }

  @Async
  @Override
  public CompletableFuture<Either<Failure, List<User>>> getAllUsers(int authorizedUserId) {
    User authorizedUser = userRepository.findById(authorizedUserId).get();
    if (authorizedUser.getRole() == Role.ADMIN) {
    return CompletableFuture.completedFuture(Either.right(userRepository.findAll()));}
    else return CompletableFuture.completedFuture(Either.right(userRepository
            .findAll()
            .stream()
            .filter(user -> user.getRole() == Role.CONTENT_MAKER)
            .toList()
    ));}



  @Async
  @Override
  public CompletableFuture<Either<Failure, User>> getUserByEmail(String email) {
    if (isEmailExists(email)) {
      return CompletableFuture.completedFuture(Either.right(
            userRepository.findOneByEmail(email).get()));
    } else {
      return CompletableFuture.completedFuture(Either.left(
            new NotFound(Messages.USER_NOT_FOUND)));
    }



  }

  public boolean isEmailExists(String email) {
    return userRepository.findOneByEmail(email).isPresent();
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
      return userRepository.findOneByEmail(email).get().getId() == id;
    }
    return false;
  }


}
