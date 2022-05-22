package com.example.mediacontentsystem.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.mediacontentsystem.domain.entities.failures.NotFound;
import com.example.mediacontentsystem.domain.entities.user.Role;
import com.example.mediacontentsystem.domain.entities.user.RoleType;
import com.example.mediacontentsystem.domain.entities.user.User;
import com.example.mediacontentsystem.domain.repositories.UserRepository;
import com.example.mediacontentsystem.domain.usecases.UseCaseUsingParams;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class UpdateUserTest {
  static UserRepository mockUserRepository;
  static UpdateUser useCase;

  @BeforeAll
  static void setUp() {
    mockUserRepository = Mockito.mock(UserRepository.class);
    useCase = new UpdateUser(mockUserRepository);
  }

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCaseUsingParams.class);
  }

  @Test
  void shouldCallMethodUpdateFromTheRepository() {
    // Arrange
    var user = new User(
        1,
        "Иванов Иван",
        "test@mail.ru",
        "1234567",
        new byte[] {},
        new Role(1, RoleType.ADMIN)
    );

    // Act
    useCase.execute(user);

    // Assert
    Mockito.verify(mockUserRepository).update(user);
  }

  @Test
  void shouldGetUpdateUserFromTheRepository() throws ExecutionException, InterruptedException {
    // Arrange
    var user = new User(
        1,
        "Иванов Иван",
        "test@mail.ru",
        "1234567",
        new byte[] {},
        new Role(1, RoleType.ADMIN)
    );

    Mockito
        .when(mockUserRepository.update(user))
        .thenReturn(CompletableFuture.completedFuture(Either.right(user)));

    // Act
    var result = useCase.execute(user).get().get();

    // Assert
    assertThat(result).isEqualTo(user);
  }

  @Test
  void notExistsUser_shouldReturnNotFound() throws ExecutionException, InterruptedException {
    // Arrange
    var failure = new NotFound();

    var user = new User(
        1,
        "Иванов Иван",
        "test@mail.ru",
        "1234567",
        new byte[] {},
        new Role(1, RoleType.ADMIN)
    );

    Mockito
        .when(mockUserRepository.update(user))
        .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(user).get().getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}
