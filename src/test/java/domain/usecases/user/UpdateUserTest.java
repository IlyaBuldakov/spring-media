package domain.usecases.user;

import domain.entities.failures.NotFound;
import domain.entities.user.User;
import domain.entities.user.Users;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * @author IlyaBuldakov
 */

@ExtendWith(MockitoExtension.class)
class UpdateUserTest {

    UserRepository mockUserRepository = mock(UserRepository.class);
    UpdateUser useCase = new UpdateUser(mockUserRepository);

    @Test
    void shouldInheritUseCase() {
        assertThat(useCase).isInstanceOf(UseCase.class);
    }

    @Test
    void shouldUpdateUserInRepository() {
        final User user = Users.createTestUser();

        useCase.execute(user);

        verify(mockUserRepository).update(user);
    }

    @Test
    void userExists_shouldUpdateUser() throws ExecutionException, InterruptedException {
        final User user = Users.createTestUser();

        when(mockUserRepository.update(user))
                .thenReturn(CompletableFuture.completedFuture(Either.right(user)));

        var result = useCase.execute(user)
                .get()
                .get();

        assertThat(result).isEqualTo(user);
    }

    @Test
    void userDoesNotExist_ShouldReturnNotFound()
            throws ExecutionException, InterruptedException {
        final User user = Users.createTestUser();

        when(mockUserRepository.update(user))
                .thenReturn(CompletableFuture.completedFuture(Either.left(new NotFound(""))));

        var result = useCase.execute(user)
                .get()
                .getLeft();

        assertThat(result).isInstanceOf(NotFound.class);
    }
}