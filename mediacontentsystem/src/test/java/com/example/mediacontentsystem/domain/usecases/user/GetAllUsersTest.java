package com.example.mediacontentsystem.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.mediacontentsystem.domain.entities.failures.NotFound;
import com.example.mediacontentsystem.domain.entities.user.Role;
import com.example.mediacontentsystem.domain.entities.user.RoleType;
import com.example.mediacontentsystem.domain.entities.user.User;
import com.example.mediacontentsystem.domain.repositories.UserRepository;
import com.example.mediacontentsystem.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GetAllUsersTest {
  static Iterable<User> users;
  static UserRepository mockUserRepository;
  static GetAllUsers useCase;

  @BeforeAll
  static void setUp() {
    users = List.of(
        new User(
            1,
            "Иванов Иван",
            "test@mail.ru",
            "123456",
            new byte[] {},
            new Role(1, RoleType.ADMIN)
        ),
        new User(
            2,
            "Иванов Иван",
            "test@mail.ru",
            "123456",
            new byte[] {},
            new Role(1, RoleType.MANAGER)
        ),
        new User(
            3,
            "Иванов Иван",
            "test@mail.ru",
            "123456",
            new byte[] {},
            new Role(1, RoleType.CONTENT_MAKER)
        )
    );

    mockUserRepository = Mockito.mock(UserRepository.class);
    useCase = new GetAllUsers(mockUserRepository);
  }

  @Test
  void shouldInheritUseCase() {
    // assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldCallMethodGetAllFromTheRepository() {
    // Act
    useCase.execute();

    // Assert
    Mockito.verify(mockUserRepository).getAll();
  }

  @Test
  void shouldGetAllUsersFromTheRepository() throws ExecutionException, InterruptedException {
    // Arrange
    Mockito
        .when(mockUserRepository.getAll())
        .thenReturn(CompletableFuture.completedFuture(Either.right(users)));

    // Act
    var result = useCase.execute().get().get();

    // Assert
    assertThat(result).isEqualTo(users);
  }

  @Test
  void notExistsUsers_shouldReturnNotFound() throws ExecutionException, InterruptedException {
    // Arrange
    var failure = new NotFound();
    Mockito
        .when(mockUserRepository.getAll())
        .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute().get().getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}
