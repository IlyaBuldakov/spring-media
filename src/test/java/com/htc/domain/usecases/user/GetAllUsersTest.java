package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.user.User;
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
class GetAllUsersTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final GetAllUsers useCase = new GetAllUsers(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldGetAllUsersFromTheRepository() {
    useCase.execute(null);
    Mockito.verify(mockUserRepository).getAll();
  }

  @Test
  void usersExist_ShouldReturnAllUsers() throws ExecutionException, InterruptedException {
    var users = List.of(
            UserService.createTestUser()
    );
    Mockito
            .when(mockUserRepository.getAll())
            .thenReturn(CompletableFuture.completedFuture(Either.right(users)));
    var result = useCase.execute(null)
            .get()
            .get();
    assertThat(result).isEqualTo(users);
  }

  @Test
  void usersNotExist_ShouldReturnEmptyList() throws ExecutionException, InterruptedException {
    var users = List.<User>of();
    Mockito
            .when(mockUserRepository.getAll())
            .thenReturn(CompletableFuture.completedFuture(Either.right(users)));
    var result = useCase.execute(null)
            .get()
            .get();
    assertThat(result).isEqualTo(users);
  }
}
