package com.htc.infrastructure.repositories.repositoriesimplementation;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.EntityName;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.user.UserEmail;
import com.htc.domain.entities.utility.parameters.user.UserImage;
import com.htc.domain.entities.utility.parameters.user.UserPassword;
import com.htc.domain.repositories.UserRepository;
import com.htc.infrastructure.models.user.UserModel;
import com.htc.infrastructure.repositories.Users;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория пользователя.
 */
@Repository
@AllArgsConstructor
public class UserRepositoryImplementation implements UserRepository {
  Users users;

  @Override
  public CompletableFuture<Either<Failure, User>> add(EntityName name,
                                                      UserEmail email,
                                                      UserPassword password,
                                                      UserImage image,
                                                      Role role) {
    // TODO реализовать проверку на уникальность почты.
    var userModel = new UserModel(
            0L,
            name.getValue(),
            password.getValue(),
            email.getValue(),
            image.getValue(),
            role.getName());
    return EitherHelper.goodRight(users.save(userModel));
  }

  @Override
  public CompletableFuture<Either<Failure, User>> get(Id id) {
    // TODO проверка на наличие в базе записи
    return users.findById((long) id.getValue()).thenApply(Either::right);
  }

  @Override
  public CompletableFuture<Either<Failure, User>> get(UserEmail email) {
    return users.findByEmail(email.getValue()).thenApply(Either::right);
  }

  @Override
  public CompletableFuture<Either<Failure, List<User>>> getAll() {
    var userList = users.findAll().stream().map(userModel -> (User) userModel).toList();
    return EitherHelper.goodRight(userList);
  }

  @Override
  public CompletableFuture<Either<Failure, User>> update(Id id,
                                                         EntityName name,
                                                         UserEmail email,
                                                         UserPassword password,
                                                         UserImage image,
                                                         Role role) {
    var userModel = new UserModel(
            id.getValue(),
            name.getValue(),
            password.getValue(),
            email.getValue(),
            image.getValue(),
            role.getName());
    return EitherHelper.goodRight(users.save(userModel));
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    users.deleteById(id.getValue());
    return EitherHelper.goodRight(null);
  }
}