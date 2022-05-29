package com.example.mediacontentsystem.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.mediacontentsystem.domain.entities.failures.NotFound;
import com.example.mediacontentsystem.domain.entities.user.Role;
import com.example.mediacontentsystem.domain.entities.user.RoleType;
import com.example.mediacontentsystem.domain.entities.user.User;
import com.example.mediacontentsystem.domain.repositories.UserRepository;
import com.example.mediacontentsystem.domain.usecases.UseCaseUsingParams;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FilterUsersByNameTest {
  static UserRepository mockUserRepository;
  static FilterUsersByName useCase;

  @BeforeAll
  static void setUp() {
    mockUserRepository = Mockito.mock(UserRepository.class);
    useCase = new FilterUsersByName(mockUserRepository);
  }

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCaseUsingParams.class);
  }

  @Test
  void shouldCallMethodFilterFromTheRepository() {
    // Arrange
    var name = "Иван";

    // Act
    useCase.execute(name);

    // Assert
    Mockito.verify(mockUserRepository).filter(name);
  }

  @Test
  void shouldFilterUsersByName_shouldReturnUsers() throws ExecutionException, InterruptedException {
    // Arrange
    var name = "Виктор";
    var users = List.of(
        new User(
            1,
            "Иванов Виктор",
            "test@mail.ru",
            "123456",
            new byte[] {},
            new Role(1, RoleType.ADMIN)
        ),
        new User(
            2,
            "Петров Виктор",
            "test@mail.ru",
            "123456",
            new byte[] {},
            new Role(1, RoleType.MANAGER)
        ),
        new User(
            3,
            "Сидоров Виктор",
            "test@mail.ru",
            "123456",
            new byte[] {},
            new Role(1, RoleType.CONTENT_MAKER)
        )
    );

    Mockito
        .when(mockUserRepository.filter(name))
        .thenReturn(CompletableFuture.completedFuture(Either.right(users)));

    // Act
    var result = useCase.execute(name).get().get();

    // Assert
    assertThat(result).isEqualTo(users);
  }

  @Test
  void notExistsUsers_shouldReturnNotFound()
      throws ExecutionException, InterruptedException {
    // Arrange
    var name = "Василий";
    var failure = new NotFound();

    Mockito
        .when(mockUserRepository.filter(name))
        .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(name).get().getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}
