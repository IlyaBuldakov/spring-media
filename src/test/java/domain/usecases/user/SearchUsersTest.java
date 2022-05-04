package domain.usecases.user;

import domain.entities.user.UserRole;
import domain.entities.user.Users;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

/**
 * @author IlyaBuldakov
 */

@ExtendWith(MockitoExtension.class)
class SearchUsersTest {

    final UserRepository mockUserRepository = mock(UserRepository.class);
    final SearchUsers useCase = new SearchUsers(mockUserRepository);

    @Test
    void shouldInheritUseCase() {
        assertThat(useCase).isInstanceOf(UseCase.class);
    }

    @Test
    void queryDoesNotContainRole_ShouldCallSearchMethodWithoutRoleArgumentOnly() {
        var query = new SearchUsers.Query("test", null);

        useCase.execute(query);

        verify(mockUserRepository, times(1)).search(query.query());
        verify(mockUserRepository, times(0)).search(query.query(), query.role());
    }

    @Test
    void queryContainsRole_ShouldCallSearchMethodWithRoleArgumentOnly() {
        var query = new SearchUsers.Query(
                "test",
                new UserRole(1, UserRole.RoleType.ADMIN));

        useCase.execute(query);

        verify(mockUserRepository, times(1)).search(query.query(), query.role());
        verify(mockUserRepository, times(0)).search(query.query());
    }

    @Test
    void queryDoesNotContainRole_ShouldGetUsersFromTheRepositoryAccordingToQuery()
            throws ExecutionException, InterruptedException {
        var query = new SearchUsers.Query("test", null);
        var expected = List.of(
                Users.createTestUser()
        );

        when(mockUserRepository.search(query.query()))
                .thenReturn(CompletableFuture.completedFuture(Either.right(expected)));

        var result = useCase.execute(query)
                .get()
                .get();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void queryContainsRole_ShouldGetUserFromTheRepositoryAccordingToQuery()
            throws ExecutionException, InterruptedException {
        var query = new SearchUsers.Query(
                "test",
                new UserRole(1, UserRole.RoleType.ADMIN));
        var expected = List.of(
                Users.createTestUser()
        );

        when(mockUserRepository.search(query.query(), query.role()))
                .thenReturn(CompletableFuture.completedFuture(Either.right(expected)));

        var result = useCase.execute(query)
                .get()
                .get();

        assertThat(result).isEqualTo(expected);
    }
}

