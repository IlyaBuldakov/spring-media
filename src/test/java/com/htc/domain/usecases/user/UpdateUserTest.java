package com.htc.domain.usecases.user;

import com.htc.util.UserParams;
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

public class UpdateUserTest {

    final UsersRepository mockUsersRepository = mock(UsersRepository.class);
    final UpdateUser useCase = new UpdateUser(mockUsersRepository);
    final UserParams testUserParams = new UserParams(Users.createTestUser());

    @Test
    void shouldInheritUseCase() {
        assertThat(useCase).isInstanceOf(UseCase.class);
    }

    @Test
    void shouldUpdateUserInRepository() {
        useCase.execute(testUserParams);

        verify(mockUsersRepository).update(
                Integer.parseInt(testUserParams.getId()),
                testUserParams.getName(),
                testUserParams.getPassword(),
                testUserParams.getEmail(),
                testUserParams.getAvatar(),
                testUserParams.getRole()
        );
    }

    @Test
    void userExists_shouldUpdateUser() throws ExecutionException, InterruptedException {
        final User user = Users.createTestUser();

        when(mockUsersRepository.update(
                Integer.parseInt(testUserParams.getId()),
                testUserParams.getName(),
                testUserParams.getPassword(),
                testUserParams.getEmail(),
                testUserParams.getAvatar(),
                testUserParams.getRole()))
                .thenReturn(CompletableFuture.completedFuture(Either.right(user)));

        var result = useCase.execute(testUserParams)
                .get()
                .get();

        assertThat(result).isEqualTo(user);
    }

    @Test
    void userDoesNotExist_shouldReturnNotFound()
            throws ExecutionException, InterruptedException {
        final User user = Users.createTestUser();

        when(mockUsersRepository.update(
                Integer.parseInt(testUserParams.getId()),
                testUserParams.getName(),
                testUserParams.getPassword(),
                testUserParams.getEmail(),
                testUserParams.getAvatar(),
                testUserParams.getRole()
        )).thenReturn(CompletableFuture.completedFuture(Either.left(NotFound.USER)));

        var result = useCase.execute(testUserParams)
                .get()
                .getLeft();

        assertThat(result).isInstanceOf(NotFound.class);
    }
}