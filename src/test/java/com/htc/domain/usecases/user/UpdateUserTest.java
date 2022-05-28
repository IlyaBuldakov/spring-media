package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utilily.UserService;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateUserTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final UpdateUser useCase = new UpdateUser(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldUpdateUserByTheRepository() {
    // Arrange
    var user = UserService.createTestUser();
    // Act
    useCase.execute(user);
    // Assert
    Mockito.verify(mockUserRepository).update(user);
  }

  @Test
  void usersExist_ShouldUpdateUser() throws ExecutionException, InterruptedException {
    var user = UserService.createTestUser();
    Mockito
            .when(mockUserRepository.update(user))
            .thenReturn(CompletableFuture.completedFuture(Either.right(user)));
    var result = useCase.execute(user)
            .get()
            .get();
    assertThat(result).isEqualTo(user);
  }

  @Test
  void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    var user = UserService.createTestUser();
    var failure = new NotFound("");
    Mockito
            .when(mockUserRepository.update(user))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));
    var result = useCase.execute(user)
            .get()
            .getLeft();
    assertThat(result).isEqualTo(failure);
  }
}
