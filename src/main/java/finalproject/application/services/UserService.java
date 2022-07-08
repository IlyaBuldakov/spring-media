package finalproject.application.services;

import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface UserService {

  CompletableFuture<Either<Failure, User>> createNewUser(User user);
  CompletableFuture<Either<Failure, User>> editUser(User user, int id);
  CompletableFuture<Either<Failure, Void>> deleteUserById(int id);
  CompletableFuture<Either<Failure, User>> getUserById(int id);
  CompletableFuture<Either<Failure, List<User>>> getAllUsers();
  CompletableFuture<Either<Failure, User>> getUserByEmail(String email);

}

