package com.example.mediacontentsystem.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.mediacontentsystem.domain.entities.failures.EntityExists;
import com.example.mediacontentsystem.domain.entities.user.Role;
import com.example.mediacontentsystem.domain.entities.user.RoleType;
import com.example.mediacontentsystem.domain.entities.user.User;
import com.example.mediacontentsystem.domain.repositories.UserRepository;
import com.example.mediacontentsystem.domain.usecases.UseCaseUsingParams;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CreateUserTest {
  static User user;
  static UserRepository mockUserRepository;
  static CreateUser useCase;

  @BeforeAll
  static void setUp() {
    user = new User(
        1,
        "Иванов Иван",
        "test@mail.ru",
        "123456",
        new byte[] {},
        new Role(1, RoleType.ADMIN)
    );

    mockUserRepository = Mockito.mock(UserRepository.class);
    useCase = new CreateUser(mockUserRepository);
  }

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCaseUsingParams.class);
  }

  @Test
  void shouldCallMethodCreateFromTheRepository() {
    // Arrange
    var user = new User(
        1,
        "Иванов Иван",
        "test@mail.ru",
        "123456",
        new byte[] {},
        new Role(1, RoleType.ADMIN)
    );

    // Act
    useCase.execute(user);

    // Assert
    Mockito.verify(mockUserRepository).create(user);
  }

  @Test
  void shouldCreateUser_shouldReturnUser() throws ExecutionException, InterruptedException {
    // Arrange
    Mockito
        .when(mockUserRepository.create(user))
        .thenReturn(CompletableFuture.completedFuture(Either.right(user)));

    // Act
    var result = useCase.execute(user).get().get();

    // Assert
    assertThat(result).isEqualTo(user);
  }

  @Test
  void createExistsUser_shouldReturnEntityExists() throws ExecutionException, InterruptedException {
    // Arrange
    var failure = new EntityExists();
    Mockito
        .when(mockUserRepository.create(user))
        .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(user).get().getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}
