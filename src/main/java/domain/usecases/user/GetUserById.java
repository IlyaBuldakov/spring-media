package domain.usecases.user;

import domain.entities.failures.Failure;
import domain.entities.user.User;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.util.concurrent.Future;

/**
 * Реализация сценария получения пользователя по идентификатору
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public final class GetUserById implements UseCase<Integer, User> {

    private final UserRepository userRepository;

    @Override
    public Future<Either<Failure, User>> execute(Integer param) {
        userRepository.get(1);
        return null;
    }
}
