package domain.usecases.user;

import domain.entities.failures.Failure;
import domain.entities.user.User;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;

import java.util.concurrent.Future;

/**
 * Реализация сценария создания пользователя
 *
 * @author IlyaBuldakov
 */
public class CreateUser implements UseCase<User, User> {

    UserRepository userRepository;

    @Override
    public Future<Either<Failure, User>> execute(User param) {
        return userRepository.create(param);
    }
}
