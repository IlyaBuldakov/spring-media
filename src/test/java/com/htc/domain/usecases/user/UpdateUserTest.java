package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.user.User;
import com.htc.util.Users;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * @author IlyaBuldakov
 */
public class UpdateUserTest {

    final UsersRepository mockUsersRepository = mock(UsersRepository.class);
    final UpdateUser useCase = new UpdateUser(mockUsersRepository);

    public static UpdateUser mockUpdateUser = mock(UpdateUser.class);

    @Test
    void shouldInheritUseCase() {
        assertThat(useCase).isInstanceOf(UseCase.class);
    }

    @Test
    void shouldUpdateUserInRepository() {
        final User user = Users.createTestUser();

        useCase.execute(user);

        verify(mockUsersRepository).update(user);
    }

    @Test
    void userExists_shouldUpdateUser() throws ExecutionException, InterruptedException {
        final User user = Users.createTestUser();

        when(mockUsersRepository.update(user))
                .thenReturn(CompletableFuture.completedFuture(Either.right(user)));

        var result = useCase.execute(user)
                .get()
                .get();

        assertThat(result).isEqualTo(user);
    }

    @Test
    void userDoesNotExist_shouldReturnNotFound()
            throws ExecutionException, InterruptedException {
        final User user = Users.createTestUser();

        when(mockUsersRepository.update(user))
                .thenReturn(CompletableFuture.completedFuture(Either.left(new NotFound(""))));

        var result = useCase.execute(user)
                .get()
                .getLeft();

        assertThat(result).isInstanceOf(NotFound.class);
    }
}