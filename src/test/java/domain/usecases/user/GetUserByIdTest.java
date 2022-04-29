package domain.usecases.user;

import domain.entities.failures.NotFound;
import domain.entities.user.User;
import domain.entities.user.UserRole;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author IlyaBuldakov
 */

@ExtendWith(MockitoExtension.class)
class GetUserByIdTest {

    final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
    final GetUserById useCase = new GetUserById(mockUserRepository);

    @Test
    void shouldInheritUseCase() {
        assertThat(useCase).isInstanceOf(UseCase.class);
    }

    @Test
    void shouldGetUserFromRepository() {
        var userId = new Random().nextInt();

        useCase.execute(userId);

        verify(mockUserRepository).get(userId);
    }

    @Test
    void userExists_shouldReturnUser() throws ExecutionException, InterruptedException {
        var userId = new Random().nextInt();

        final User user = new User(
                userId,
                "Бузова Ольга",
                "password",
                "example@mail.ru",
                new byte[]{},
                new UserRole(1, UserRole.RoleType.ADMIN)
        );

        when(mockUserRepository.get(userId))
                .thenReturn(CompletableFuture.completedFuture(Either.right(user)));

        var result = useCase.execute(userId)
                .get()
                .get();

        assertThat(result).isEqualTo(user);
    }

    @Test
    void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
        var userId = new Random().nextInt();
        var failure = new NotFound();

        when(mockUserRepository.get(userId))
                .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

        var result = useCase.execute(userId)
                .get()
                .getLeft();

        assertThat(result).isEqualTo(failure);
    }

}