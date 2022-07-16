package com.htc.domain.usecases.user;

import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class GetAllUsersTest {

    private final UsersRepository MOCK_USERS_REPOSITORY = mock(UsersRepository.class);

    private final GetAllUsers GET_ALL_USERS = new GetAllUsers(MOCK_USERS_REPOSITORY);

    private final User MOCK_USER = mock(User.class);

    @BeforeEach
    public void beforeEach() {
        when(MOCK_USERS_REPOSITORY.getAll()).thenReturn(
                CompletableFuture.completedFuture(Either.right(
                        List.of(MOCK_USER)
                ))
        );
    }

    @Test
    public void shouldCallUsersRepositoryMethod() {
        GET_ALL_USERS.execute();
        verify(MOCK_USERS_REPOSITORY).getAll();
    }

    @Test
    public void shouldReturnListOfUser() throws ExecutionException, InterruptedException {
        var retVal = GET_ALL_USERS.execute();
        User listElement = retVal.get().get().get(0);

        assertThat(retVal.get().get()).isNotNull();
        assertThat(listElement).isInstanceOf(User.class);
    }
}