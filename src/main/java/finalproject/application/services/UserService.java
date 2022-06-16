package finalproject.application.services;

import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import finalproject.domain.repositories.UserRepository;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface UserService {

  CompletableFuture<Either<Failure, User>> createNewUser(User user);
  CompletableFuture<Either<Failure, Void>> editUser(User user);
  CompletableFuture<Either<Failure, Void>> deleteUser(User user);
  CompletableFuture<Either<Failure, User>> getUserById(int id);
  CompletableFuture<Either<Failure, List<User>>> getAllUsers();
  CompletableFuture<Either<Failure, List<User>>> getUsersByQuery(String query);

}

