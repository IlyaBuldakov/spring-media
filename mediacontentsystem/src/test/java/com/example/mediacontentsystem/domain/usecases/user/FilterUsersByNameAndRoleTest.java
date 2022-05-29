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
class FilterUsersByNameAndRoleTest {
  static UserRepository mockUserRepository;
  static FilterUsersByNameAndRole useCase;

  @BeforeAll
  static void setUp() {
    mockUserRepository = Mockito.mock(UserRepository.class);
    useCase = new FilterUsersByNameAndRole(mockUserRepository);
  }

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCaseUsingParams.class);
  }

  @Test
  void shouldCallMethodFilterFromTheRepository() {
    // Arrange
    var params = new FilterUsersByNameAndRole.Query("Иван", RoleType.ADMIN);

    // Act
    useCase.execute(params);

    // Assert
    Mockito.verify(mockUserRepository).filter(params.getName(), params.getRole());
  }

  @Test
  void shouldFilterUsersByNameAndRole_shouldReturnUsers()
      throws ExecutionException, InterruptedException {
    // Arrange
    var params = new FilterUsersByNameAndRole.Query("Антон", RoleType.ADMIN);
    var users = List.of(
        new User(
            1,
            "Иванов Антон",
            "test@mail.ru",
            "123456",
            new byte[] {},
            new Role(1, RoleType.ADMIN)
        ),
        new User(
            2,
            "Петров Антон",
            "test@mail.ru",
            "123456",
            new byte[] {},
            new Role(1, RoleType.ADMIN)
        ),
        new User(
            3,
            "Сидоров Антон",
            "test@mail.ru",
            "123456",
            new byte[] {},
            new Role(1, RoleType.ADMIN)
        )
    );

    Mockito
        .when(mockUserRepository.filter(params.getName(), params.getRole()))
        .thenReturn(CompletableFuture.completedFuture(Either.right(users)));

    // Act
    var result = useCase.execute(params).get().get();

    // Assert
    assertThat(result).isEqualTo(users);
  }

  @Test
  void notExistsUsers_shouldReturnNotFound()
      throws ExecutionException, InterruptedException {
    // Arrange
    var params = new FilterUsersByNameAndRole.Query("Виктор", RoleType.CONTENT_MAKER);
    var failure = new NotFound();

    Mockito
        .when(mockUserRepository.filter(params.getName(), params.getRole()))
        .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(params).get().getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}
