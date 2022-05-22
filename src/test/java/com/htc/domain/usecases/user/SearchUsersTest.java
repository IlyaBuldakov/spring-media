package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.user.User;
import com.htc.domain.entities.user.UserRole;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchUsersTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final SearchUsers useCase = new SearchUsers(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void queryDoesNotContainsRole_ShouldCallSearchMethodWithoutRoleArgumentOnly() {
    var query = new SearchUsers.Query("query_test", null);
    useCase.execute(query);
    Mockito.verify(mockUserRepository, Mockito.times(1)).search(query.query());
    Mockito.verify(mockUserRepository, Mockito.times(0)).search(query.query(), query.role());
  }

  @Test
  void queryContainsRole_ShouldCallSearchMethodWithRoleArgumentOnly() {
    var query = new SearchUsers.Query("query_test", new UserRole(1, UserRole.RoleType.ADMIN));
    useCase.execute(query);
    Mockito.verify(mockUserRepository, Mockito.times(1)).search(query.query(), query.role());
    Mockito.verify(mockUserRepository, Mockito.times(0)).search(query.query());
  }

  @Test
  void queryDoesNotContainsRole_ShouldGetUsersFromTheRepositoryAccordingToQuery()
          throws ExecutionException, InterruptedException {
    // Arrange
    var query = new SearchUsers.Query("user_test", null);
    var expected = List.of(
            User.createTestUser()
    );
    Mockito
            .when(mockUserRepository.search(query.query()))
            .thenReturn(CompletableFuture.completedFuture(Either.right(expected)));
    // Act
    var result = useCase.execute(query)
            .get()
            .get();

    // Assert
    assertThat(result).isEqualTo(expected);
  }

  @Test
  void queryContainsRole_ShouldGetUsersFromTheRepositoryAccordingToQuery()
          throws ExecutionException, InterruptedException {
    var query = new SearchUsers.Query("query_test", new UserRole(1, UserRole.RoleType.ADMIN));
    var expected = List.of(
            User.createTestUser()
    );
    Mockito
            .when(mockUserRepository.search(query.query(), query.role()))
            .thenReturn(CompletableFuture.completedFuture(Either.right(expected)));
    var result = useCase.execute(query)
            .get()
            .get();
    assertThat(result).isEqualTo(expected);
  }
}
