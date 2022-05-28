package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.failures.AlreadyExists;
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
class AddUserTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final AddUser useCase = new AddUser(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldCreateUserByTheRepository() {
    // Arrange
    var user = UserService.createTestUser();
    // Act
    useCase.execute(user);
    // Assert
    Mockito.verify(mockUserRepository).add(user);
  }

  @Test
  void userDoesNotExist_ShouldCreateUserAndReturnUser()
          throws ExecutionException, InterruptedException {
    var user = UserService.createTestUser();
    Mockito
            .when(mockUserRepository.add(user))
            .thenReturn(CompletableFuture.completedFuture(Either.right(user)));
    var result = useCase.execute(user)
            .get()
            .get();
    assertThat(result).isEqualTo(user);
  }

  @Test
  void usersExist_ShouldReturnAlreadyExists() throws ExecutionException, InterruptedException {
    var user = UserService.createTestUser();
    var failure = new AlreadyExists("");
    Mockito
            .when(mockUserRepository.add(user))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));
    var result = useCase.execute(user)
            .get()
            .getLeft();
    assertThat(result).isEqualTo(failure);
  }
}
