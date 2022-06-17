package com.htc.domain.usecases.user;

import com.htc.domain.entities.user.Role;
import com.htc.util.Users;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author IlyaBuldakov
 */

@ExtendWith(MockitoExtension.class)
class SearchUsersTest {

    final UsersRepository mockUsersRepository = mock(UsersRepository.class);
    final SearchUsers useCase = new SearchUsers(mockUsersRepository);

    @Test
    void shouldInheritUseCase() {
        assertThat(useCase).isInstanceOf(UseCase.class);
    }

    @Test
    void queryDoesNotContainRole_shouldCallSearchMethodWithoutRoleArgumentOnly() {
        var query = new SearchUsers.Query("test", null);

        useCase.execute(query);

        verify(mockUsersRepository, times(1)).search(query.query());
        verify(mockUsersRepository, times(0)).search(query.query(), query.role());
    }

    @Test
    void queryContainsRole_shouldCallSearchMethodWithRoleArgumentOnly() {
        var query = new SearchUsers.Query(
                "test",
                new Role(1, Role.RoleType.ADMIN));

        useCase.execute(query);

        verify(mockUsersRepository, times(1)).search(query.query(), query.role());
        verify(mockUsersRepository, times(0)).search(query.query());
    }

    @Test
    void queryDoesNotContainRole_shouldGetUsersFromTheRepositoryAccordingToQuery()
            throws ExecutionException, InterruptedException {
        var query = new SearchUsers.Query("test", null);
        var expected = List.of(
                Users.createTestUser()
        );

        when(mockUsersRepository.search(query.query()))
                .thenReturn(CompletableFuture.completedFuture(Either.right(expected)));

        var result = useCase.execute(query)
                .get()
                .get();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void queryContainsRole_shouldGetUserFromTheRepositoryAccordingToQuery()
            throws ExecutionException, InterruptedException {
        var query = new SearchUsers.Query(
                "test",
                new Role(1, Role.RoleType.ADMIN));
        var expected = List.of(
                Users.createTestUser()
        );

        when(mockUsersRepository.search(query.query(), query.role()))
                .thenReturn(CompletableFuture.completedFuture(Either.right(expected)));

        var result = useCase.execute(query)
                .get()
                .get();

        assertThat(result).isEqualTo(expected);
    }
}

