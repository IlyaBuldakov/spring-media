package com.htc.domain.usecases.user;

import com.htc.domain.entities.failure.InvalidValuesContainer;
import com.htc.domain.entities.failure.NotFound;
import com.htc.domain.repositories.UsersRepository;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class DeleteUserByIdTest {

    // Валидный идентификатор пользователя.
    private final String ID = "1";
    private final UsersRepository MOCK_USERS_REPOSITORY = mock(UsersRepository.class);

    private final DeleteUserById DELETE_USER = new DeleteUserById(MOCK_USERS_REPOSITORY);

    @BeforeEach
    public void beforeEach() {
        when(MOCK_USERS_REPOSITORY.deleteById(Integer.parseInt(ID)))
                .thenReturn(CompletableFuture.completedFuture(Either.right(null)));
    }

    @Test
    public void shouldCallUsersRepositoryMethod_withInitialParams() {
        DELETE_USER.execute(ID);
        verify(MOCK_USERS_REPOSITORY).deleteById(Integer.parseInt(ID));
    }

    @Test
    public void validId_shouldReturnVoid() throws ExecutionException, InterruptedException {
        var retVal = DELETE_USER.execute(ID);
        assertThat(retVal.get().get()).isNull();
    }

    @Test
    public void invalidId_shouldReturnInvalidValuesContainer_aboutId() throws ExecutionException, InterruptedException {
        var retVal = DELETE_USER.execute("abc");

        var expectedFailure = retVal.get().getLeft();
        assertThat(expectedFailure).isNotNull().isInstanceOf(InvalidValuesContainer.class);

        var invalidValuesContainer = (InvalidValuesContainer) expectedFailure;
        assertThat(invalidValuesContainer.getInvalidValues().get(0).getField()).isEqualTo("id");
    }

    @Test
    public void userNotFound_shouldReturnNotFound() throws ExecutionException, InterruptedException {
        when(MOCK_USERS_REPOSITORY.deleteById(Integer.parseInt(ID)))
                .thenReturn(CompletableFuture.completedFuture(Either.left(NotFound.USER)));
        var retVal = DELETE_USER.execute(ID);

        assertThat(retVal.get().getLeft()).isNotNull().isInstanceOf(NotFound.class);
    }
}