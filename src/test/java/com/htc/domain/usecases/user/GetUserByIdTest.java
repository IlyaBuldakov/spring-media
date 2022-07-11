package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.user.User;
import com.htc.util.Users;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author IlyaBuldakov
 */
public class GetUserByIdTest {

    final UsersRepository mockUsersRepository = mock(UsersRepository.class);
    final GetUserById useCase = new GetUserById(mockUsersRepository);

    public static GetUserById mockGetUserById = mock(GetUserById.class);

    @Test
    void shouldInheritUseCase() {
        assertThat(useCase).isInstanceOf(UseCase.class);
    }

    @Test
    void shouldGetUserFromRepository() {
        var userId = String.valueOf(new Random().nextInt(255));

        useCase.execute(userId);

        verify(mockUsersRepository).getById(Integer.parseInt(userId));
    }


    /**
     * Тест-проверка на факт того, что пользователь получен именно тот,
     * который был запрошен. Дополнительно проверяется идентификатор
     */
    @Test
    void userExists_shouldReturnUser() throws ExecutionException, InterruptedException {
        var userId = String.valueOf(new Random().nextInt(255));

        final User user = Users.createTestUser(Integer.parseInt(userId));

        when(mockUsersRepository.getById(Integer.parseInt(userId)))
                .thenReturn(CompletableFuture.completedFuture(Either.right(user)));

        var result = useCase.execute(userId)
                .get()
                .get();

        assertThat(result.getId()).isEqualTo(user.getId());
        assertThat(result).isEqualTo(user);
    }

    @Test
    void userDoesNotExist_shouldReturnNotFound() throws ExecutionException, InterruptedException {
        var userId = String.valueOf(new Random().nextInt(255));
        var failure = new NotFound("");

        when(mockUsersRepository.getById(Integer.parseInt(userId)))
                .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

        var result = useCase.execute(userId)
                .get()
                .getLeft();

        assertThat(result).isEqualTo(failure);
    }

}