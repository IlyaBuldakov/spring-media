package domain.usecases.user;

import domain.entities.failures.Failure;
import domain.entities.user.User;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.util.concurrent.Future;

/**
 * Реализация сценария обновления пользователя
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public final class UpdateUser implements UseCase<User, User> {

    private final UserRepository userRepository;

    @Override
    public Future<Either<Failure, User>> execute(User param) {
        return userRepository.update(param);
    }
}
