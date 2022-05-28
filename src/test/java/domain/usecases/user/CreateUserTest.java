package domain.usecases.user;

import domain.entities.failures.AlreadyExists;
import domain.entities.user.User;
import util.Users;
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
class CreateUserTest {

    final UserRepository mockUserRepository = mock(UserRepository.class);
    final CreateUser useCase = new CreateUser(mockUserRepository);

    @Test
    void shouldInheritUseCase() {
        assertThat(useCase).isInstanceOf(UseCase.class);
    }

    @Test
    void shouldCreateInRepository() {
        final User user = Users.createTestUser();

        useCase.execute(user);

        verify(mockUserRepository).create(user);
    }

    @Test
    void userNotExists_shouldCreateAndReturnUser() throws ExecutionException, InterruptedException {
        final User user = Users.createTestUser();

        when(mockUserRepository.create(user))
                .thenReturn(CompletableFuture.completedFuture(Either.right(user)));

        var result = useCase.execute(user)
                .get()
                .get();

        assertThat(result).isEqualTo(user);
    }

    @Test
    void userExists_shouldReturnAlreadyExists() throws ExecutionException, InterruptedException {
        final User user = Users.createTestUser();

        when(mockUserRepository.create(user))
                .thenReturn(CompletableFuture.completedFuture(Either.left(new AlreadyExists(""))));

        var result = useCase.execute(user)
                .get()
                .getLeft();

        assertThat(result).isInstanceOf(AlreadyExists.class);
    }
}