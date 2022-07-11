package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.infrastructure.jpa.UsersRepositoryImpl;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария получения всех пользователей.
 */
@AllArgsConstructor
@Component
public class GetAllUsers implements UseCase<Void, List<User>> {

    /**
     * Поле для внедрения реализации из infrastructure layer.
     * Пример: {@link UsersRepositoryImpl}.
     */
    private UsersRepository usersRepository;

    /**
     * Метод сценария.
     *
     * @param param Параметр-заглушка.
     * @return Список пользователей.
     */
    @Override
    public CompletableFuture<Either<Failure, List<User>>> execute(Void param) {
        return usersRepository.getAll();
    }
}
