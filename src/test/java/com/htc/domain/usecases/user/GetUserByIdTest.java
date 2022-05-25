package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserByIdTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final GetUserById useCase = new GetUserById(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldGetUserFromTheRepository() {
    var testUserId = new Random().nextInt();
    useCase.execute(testUserId);
    Mockito.verify(mockUserRepository).get(testUserId);
  }

  @Test
  void userExists_ShouldReturnUser() throws ExecutionException, InterruptedException {
    // Arrange
    var testUserId = new Random().nextInt();
    var user = UserService.createTestUser(testUserId);
    Mockito
            .when(mockUserRepository.get(testUserId))
            .thenReturn(CompletableFuture.completedFuture(Either.right(user)));
    // Act
    var result = useCase.execute(testUserId)
            .get()
            .get();
    // Assert
    assertThat(result).isEqualTo(user);
  }

  @Test
  void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    var badTestUserId = new Random().nextInt();
    var failure = new NotFound();
    Mockito
            .when(mockUserRepository.get(badTestUserId))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));
    var result = useCase.execute(badTestUserId)
            .get()
            .getLeft();
    assertThat(result).isEqualTo(failure);
  }
}
