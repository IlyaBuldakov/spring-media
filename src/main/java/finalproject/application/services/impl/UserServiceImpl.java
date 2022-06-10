package finalproject.application.services.impl;

import finalproject.application.services.UserService;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import finalproject.domain.repositories.UserRepository;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Override
  public Future<Either<Failure, Integer>> createNewUser(User user) {
    user = repository.save(user);
  return null;
  }

  @Override
  public Future<Either<Failure, Void>> editUser(User user) {
    return null;
  }

  @Override
  public Future<Either<Failure, Void>> deleteUser(User user) {
    return null;
  }

  @Override
  public Future<Either<Failure, User>> getUserById(int id) {
    return null;
  }

  @Override
  public Future<Either<Failure, List<User>>> getAllUsers() {
    return null;
  }

  @Override
  public Future<Either<Failure, List<User>>> getUsersByQuery(String query) {
    return null;
  }
}
