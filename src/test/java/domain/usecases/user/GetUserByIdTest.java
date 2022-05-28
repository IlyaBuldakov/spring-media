package domain.usecases.user;

import domain.entities.failures.NotFound;
import domain.entities.user.User;
import util.Users;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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

@ExtendWith(MockitoExtension.class)
class GetUserByIdTest {

    final UserRepository mockUserRepository = mock(UserRepository.class);
    final GetUserById useCase = new GetUserById(mockUserRepository);

    @Test
    void shouldInheritUseCase() {
        assertThat(useCase).isInstanceOf(UseCase.class);
    }

    @Test
    void shouldGetUserFromRepository() {
        var userId = new Random().nextInt(255);

        useCase.execute(userId);

        verify(mockUserRepository).get(userId);
    }


    /**
     * Тест-проверка на факт того, что пользователь получен именно тот,
     * который был запрошен. Дополнительно проверяется идентификатор
     */
    @Test
    void userExists_shouldReturnUser() throws ExecutionException, InterruptedException {
        var userId = new Random().nextInt(255);

        final User user = Users.createTestUser(userId);

        when(mockUserRepository.get(userId))
                .thenReturn(CompletableFuture.completedFuture(Either.right(user)));

        var result = useCase.execute(userId)
                .get()
                .get();

        assertThat(result.getId()).isEqualTo(user.getId());
        assertThat(result).isEqualTo(user);
    }

    @Test
    void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
        var userId = new Random().nextInt(255);
        var failure = new NotFound("");

        when(mockUserRepository.get(userId))
                .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

        var result = useCase.execute(userId)
                .get()
                .getLeft();

        assertThat(result).isEqualTo(failure);
    }

}