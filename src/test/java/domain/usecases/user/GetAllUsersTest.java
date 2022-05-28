package domain.usecases.user;

import domain.entities.user.User;
import util.Users;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
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
class GetAllUsersTest {

    UserRepository mockUserRepository = mock(UserRepository.class);
    GetAllUsers useCase = new GetAllUsers(mockUserRepository);

    @Test
    void shouldInheritUseCase() {
        assertThat(useCase).isInstanceOf(UseCase.class);
    }

    @Test
    void shouldGetAllUsersFromRepository() {
        useCase.execute(null);

        verify(mockUserRepository).getAll();
    }

    @Test
    void usersListIsNotEmpty_shouldGetUsersList() throws ExecutionException, InterruptedException {

        List<User> usersList = List.of(
                Users.createTestUser(),
                Users.createTestUser(),
                Users.createTestUser()
        );

        when(mockUserRepository.getAll())
                .thenReturn(CompletableFuture.completedFuture(Either.right(usersList)));

        var result = useCase.execute(null)
                .get()
                .get();

        assertThat(result).isEqualTo(usersList);
    }

    @Test
    void usersListIsEmpty_ShouldReturnEmptyList() throws ExecutionException, InterruptedException {
        when(mockUserRepository.getAll())
                .thenReturn(CompletableFuture.completedFuture(Either.right(Collections.emptyList())));

        var result = useCase.execute(null)
                .get()
                .get();

        assertThat(result).isEqualTo(Collections.emptyList());
    }
}