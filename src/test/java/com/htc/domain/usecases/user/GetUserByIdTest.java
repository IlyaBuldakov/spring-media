package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utilily.UserService;
import com.htc.utility.EitherHelper;
import java.util.Random;
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
    var testUserId = String.valueOf(new Random().nextInt(1, 32));
    useCase.execute(new GetUserById.Params(testUserId, "id"));
    Mockito.verify(mockUserRepository).get(Integer.parseInt(testUserId));
  }

  @Test
  void userExists_ShouldReturnUser() throws ExecutionException, InterruptedException {
    // Arrange
    var testUserId = new Random().nextInt(1, 32);
    var user = UserService.createTestUser(testUserId);
    Mockito
            .when(mockUserRepository.get(testUserId))
            .thenReturn(EitherHelper.goodRight(user));
    // Act
    var result = useCase.execute(new GetUserById.Params(String.valueOf(testUserId), "id"))
            .get()
            .get();
    // Assert
    assertThat(result).isEqualTo(user);
  }

  @Test
  void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    var badTestUserId = new Random().nextInt(1, 32);
    var failure = NotFound.DEFAULT_MESSAGE;
    Mockito
            .when(mockUserRepository.get(badTestUserId))
            .thenReturn(EitherHelper.badLeft(failure));
    var result = useCase.execute(new GetUserById.Params(String.valueOf(badTestUserId), "id"))
            .get()
            .getLeft();
    assertThat(result).isEqualTo(failure);
  }
}
