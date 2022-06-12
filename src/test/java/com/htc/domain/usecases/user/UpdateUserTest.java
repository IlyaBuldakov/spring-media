package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utilily.UserService;
import com.htc.utility.EitherHelper;
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
            .thenReturn(EitherHelper.goodRight(user));
    var result = useCase.execute(user)
            .get()
            .get();
    assertThat(result).isEqualTo(user);
  }

  @Test
  void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    var user = UserService.createTestUser();
    var failure = NotFound.DEFAULT_MESSAGE;
    Mockito
            .when(mockUserRepository.update(user))
            .thenReturn(EitherHelper.badLeft(failure));
    var result = useCase.execute(user)
            .get()
            .getLeft();
    assertThat(result).isEqualTo(failure);
  }
}
