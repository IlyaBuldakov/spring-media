package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.AlreadyExists;
import com.htc.domain.entities.failures.InvalidValuesContainer;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetUserByIdTest {

    // Валидный идентификатор пользователя.
    private final String ID = "1";

    private final UsersRepository MOCK_USERS_REPOSITORY = mock(UsersRepository.class);

    private final GetUserById GET_USER_BY_ID = new GetUserById(MOCK_USERS_REPOSITORY);

    private final User MOCK_USER = mock(User.class);

    @BeforeEach
    public void beforeEach() {
        when(MOCK_USERS_REPOSITORY.getById(Integer.parseInt(ID)))
                .thenReturn(CompletableFuture.completedFuture(
                        Either.right(MOCK_USER)
                ));
    }

    @Test
    public void shouldCallUsersRepositoryMethod_withInitialParams() {
        GET_USER_BY_ID.execute(ID);
        verify(MOCK_USERS_REPOSITORY).getById(Integer.parseInt(ID));
    }

    @Test
    public void validId_shouldReturnUser() throws ExecutionException, InterruptedException {
        var retVal = GET_USER_BY_ID.execute(ID);
        assertThat(retVal.get().get()).isNotNull().isInstanceOf(User.class);
    }

    @Test
    public void invalidId_shouldReturnInvalidValuesContainer_aboutId() throws ExecutionException, InterruptedException {
        var retVal = GET_USER_BY_ID.execute("abc");

        var expectedFailure = retVal.get().getLeft();
        assertThat(expectedFailure).isNotNull().isInstanceOf(InvalidValuesContainer.class);

        var invalidValuesContainer = (InvalidValuesContainer) expectedFailure;
        assertThat(invalidValuesContainer.getInvalidValues().get(0).getField()).isEqualTo("id");
    }

    @Test
    public void userNotFound_shouldReturnNotFound() throws ExecutionException, InterruptedException {
        when(MOCK_USERS_REPOSITORY.getById(Integer.parseInt(ID)))
                .thenReturn(CompletableFuture.completedFuture(Either.left(NotFound.USER)));
        var retVal = GET_USER_BY_ID.execute(ID);

        assertThat(retVal.get().getLeft()).isNotNull().isInstanceOf(NotFound.class);
    }
}