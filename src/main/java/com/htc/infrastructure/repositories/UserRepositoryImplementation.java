package com.htc.infrastructure.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.UserEmail;
import com.htc.domain.entities.utility.parameters.UserImage;
import com.htc.domain.entities.utility.parameters.UserName;
import com.htc.domain.entities.utility.parameters.UserPassword;
import com.htc.domain.repositories.UserRepository;
import com.htc.infrastructure.models.user.UserModel;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория пользователя.
 */
@Repository
public class UserRepositoryImplementation implements UserRepository {
  @Autowired
  Users users;

  @Override
  public CompletableFuture<Either<Failure, User>> add(UserName name,
                                                      UserEmail email,
                                                      UserPassword password,
                                                      UserImage image,
                                                      Role role) {
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
    // проверка на наличие в базе записи
    return users.findById((long) id.getValue()).thenApplyAsync(Either::right);
  }

  @Override
  public CompletableFuture<Either<Failure, List<User>>> getAll() {
    //return users.getAll().thenApplyAsync(Either::right);
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, User>> update(Id id,
                                                         UserName name,
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
    //users.deleteById(id.getValue());
    return null;
  }
}
