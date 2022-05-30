package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.UserService;
import io.vavr.control.Either;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserByIdTest {
  final UserRepository mockUserRepository = mock(UserRepository.class);
  final GetUserById useCase = new GetUserById(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
   void shouldGetUserFromTheRepository() {
    // Arrange
    var userId = new Random().nextInt();

    // Act
    useCase.execute(userId);

    // Assert
    verify(mockUserRepository).get(userId);
  }

  @Test
  void userExists_ShouldReturnUser() throws ExecutionException, InterruptedException {
    // Arrange
    var userId = new Random().nextInt();
    var user = UserService.createTestUser(userId);

    when(mockUserRepository.get(userId))
        .thenReturn(CompletableFuture.completedFuture(Either.right(user)));

    // Act
    var result = useCase.execute(userId)
        .get()
        .get();

    // Assert
    assertThat(result).isEqualTo(user);
  }

  @Test
  void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    // Arrange
    var userId = new Random().nextInt();
    var failure = new NotFound("");

    when(mockUserRepository.get(userId))
        .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));
    // Act
    var result = useCase.execute(userId)
        .get()
        .getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}