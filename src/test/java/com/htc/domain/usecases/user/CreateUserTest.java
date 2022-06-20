package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.AlreadyExists;
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
public class CreateUserTest {

    final UsersRepository mockUsersRepository = mock(UsersRepository.class);
    final CreateUser useCase = new CreateUser(mockUsersRepository);

    public static CreateUser mockCreateUser = mock(CreateUser.class);

    @Test
    void shouldInheritUseCase() {
        assertThat(useCase).isInstanceOf(UseCase.class);
    }

    @Test
    void shouldCreateInRepository() {
        final User user = Users.createTestUser();

        useCase.execute(user);

        verify(mockUsersRepository).create(user);
    }

    @Test
    void userNotExists_shouldCreateAndReturnUser() throws ExecutionException, InterruptedException {
        final User user = Users.createTestUser();

        when(mockUsersRepository.create(user))
                .thenReturn(CompletableFuture.completedFuture(Either.right(user)));

        var result = useCase.execute(user)
                .get()
                .get();

        assertThat(result).isEqualTo(user);
    }

    @Test
    void userExists_shouldReturnAlreadyExists() throws ExecutionException, InterruptedException {
        final User user = Users.createTestUser();

        when(mockUsersRepository.create(user))
                .thenReturn(CompletableFuture.completedFuture(Either.left(new AlreadyExists(""))));

        var result = useCase.execute(user)
                .get()
                .getLeft();

        assertThat(result).isInstanceOf(AlreadyExists.class);
    }
}