package domain.usecases.user;

import domain.entities.failures.Failure;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;

import java.util.concurrent.Future;

/**
 * Реализация сценария удаления пользователя по идентификатору
 *
 * @author IlyaBuldakov
 */
public class DeleteUserById implements UseCase<Integer, Void> {

    UserRepository userRepository;

    @Override
    public Future<Either<Failure, Void>> execute(Integer param) {
        return userRepository.delete(param);
    }
}
