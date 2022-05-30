package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.htc.domain.entities.failures.AlreadyExists;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.UserService;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserTest {
  final UserRepository mockUserRepository = mock(UserRepository.class);
  final CreateUser useCase = new CreateUser(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldCreateUserByTheRepository() {
    // Arrange
    var user = UserService.createTestUser();

    // Act
    useCase.execute(user);

    // Assert
    verify(mockUserRepository).create(user);
  }

  @Test
  void usersNotExist_ShouldCreateUserAndReturnUser()
      throws ExecutionException, InterruptedException {
    // Arrange
    var user = UserService.createTestUser();

    when(mockUserRepository.create(user))
        .thenReturn(CompletableFuture.completedFuture(Either.right(user)));

    // Act
    var result = useCase.execute(user)
        .get()
        .get();

    // Assert
    assertThat(result).isEqualTo(user);
  }

  @Test
  void usersExist_ShouldReturnAlreadyExists()
      throws ExecutionException, InterruptedException {
    // Arrange
    var user = UserService.createTestUser();
    var failure = new AlreadyExists("");

    when(mockUserRepository.create(user))
        .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(user)
        .get()
        .getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}

