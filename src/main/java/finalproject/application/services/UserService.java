package finalproject.application.services;

import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.Future;

public interface UserService {
  Future<Either<Failure, Integer>> createNewUser(User user);
  Future<Either<Failure, Void>> editUser(User user);
  Future<Either<Failure, Void>> deleteUser(User user);
  Future<Either<Failure, User>> getUserById(int id);
  Future<Either<Failure, List<User>>> getAllUsers();
  Future<Either<Failure, List<User>>> getUsersByQuery(String query);

}
