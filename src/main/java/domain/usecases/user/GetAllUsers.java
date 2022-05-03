package domain.usecases.user;

import domain.entities.failures.Failure;
import domain.entities.user.User;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Реализация сценария получения всех пользователей
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public final class GetAllUsers implements UseCase<Void, List<User>> {

    UserRepository userRepository;

    @Override
    public Future<Either<Failure, List<User>>> execute(Void param) {
        return userRepository.getAll();
    }
}
