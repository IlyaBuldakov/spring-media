package com.htc.domain.usecases.user;

import com.htc.domain.entities.user.User;
import com.htc.util.Users;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * @author IlyaBuldakov
 */
public class GetAllUsersTest {

    final UsersRepository mockUsersRepository = mock(UsersRepository.class);
    final GetAllUsers useCase = new GetAllUsers(mockUsersRepository);

    public static GetAllUsers mockGetAllUsers = mock(GetAllUsers.class);

    @Test
    void shouldInheritUseCase() {
        assertThat(useCase).isInstanceOf(UseCase.class);
    }

    @Test
    void shouldGetAllUsersFromRepository() {
        useCase.execute(null);

        verify(mockUsersRepository).getAll();
    }

    @Test
    void usersListIsNotEmpty_shouldGetUsersList() throws ExecutionException, InterruptedException {

        List<User> usersList = List.of(
                Users.createTestUser(),
                Users.createTestUser(),
                Users.createTestUser()
        );

        when(mockUsersRepository.getAll())
                .thenReturn(CompletableFuture.completedFuture(Either.right(usersList)));

        var result = useCase.execute(null)
                .get()
                .get();

        assertThat(result).isEqualTo(usersList);
    }

    @Test
    void usersListIsEmpty_shouldReturnEmptyList() throws ExecutionException, InterruptedException {
        when(mockUsersRepository.getAll())
                .thenReturn(CompletableFuture.completedFuture(Either.right(Collections.emptyList())));

        var result = useCase.execute(null)
                .get()
                .get();

        assertThat(result).isEqualTo(Collections.emptyList());
    }
}