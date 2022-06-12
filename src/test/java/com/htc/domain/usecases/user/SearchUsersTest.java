package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.UserService;
import io.vavr.collection.List;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchUsersTest {
  final UserRepository mockUserRepository = mock(UserRepository.class);
  final SearchUsers useCase = new SearchUsers(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void queryDoesNotContainRole_ShouldCallSearchMethodWithoutRoleArgumentOnly() {
    // Arrange
    var query = new SearchUsers.Query("test", null);

    // Act
    useCase.execute(query);

    // Assert
    verify(mockUserRepository, times(1)).search(query.query());
    verify(mockUserRepository, times(0)).search(query.query(), query.role());
  }

  @Test
  void queryContainsRole_ShouldCallSearchMethodWithRoleArgumentOnly() {
    // Arrange
    var query = new SearchUsers.Query(
        "test",
        Role.ADMIN);

    // Act
    useCase.execute(query);

    // Assert
    verify(mockUserRepository, times(1)).search(query.query(), query.role());
    verify(mockUserRepository, times(0)).search(query.query());
  }

  @Test
  void queryDoesNotContainRole_ShouldGetUsersFromTheRepositoryAccordingToQuery()
      throws ExecutionException, InterruptedException {
    // Arrange
    var query = new SearchUsers.Query("test", null);
    var expected = List.of(UserService.createTestUser());

    when(mockUserRepository.search(query.query()))
        .thenReturn(CompletableFuture.completedFuture(Either.right(expected)));

    // Act
    var result = useCase.execute(query)
        .get()
        .get();

    // Assert
    assertThat(result).isEqualTo(expected);
  }

  @Test
  void queryContainsRole_ShouldGetUserFromTheRepositoryAccordingToQuery()
      throws ExecutionException, InterruptedException {
    // Arrange
    var query = new SearchUsers.Query(
        "test",
        Role.ADMIN);
    var expected = List.of(UserService.createTestUser());

    when(mockUserRepository.search(query.query(), query.role()))
        .thenReturn(CompletableFuture.completedFuture(Either.right(expected)));

    // Act
    var result = useCase.execute(query)
        .get()
        .get();

    // Assert
    assertThat(result).isEqualTo(expected);
  }
}