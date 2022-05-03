package com.example.mediacontentsystem.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.mediacontentsystem.domain.entities.failures.NotFound;
import com.example.mediacontentsystem.domain.entities.user.Role;
import com.example.mediacontentsystem.domain.entities.user.RoleType;
import com.example.mediacontentsystem.domain.entities.user.User;
import com.example.mediacontentsystem.domain.repositories.UserRepository;
import com.example.mediacontentsystem.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GetUserByIdTest {
  static int userId;
  static User user;
  static UserRepository mockUserRepository;
  static GetUserById useCase;

  @BeforeAll
  static void setUp() {
    userId = 1;

    user = new User(
        userId,
        "test@mail.ru",
        "123456",
        "Иванов Иван",
        new byte[] {},
        new Role(1, RoleType.ADMIN)
    );

    mockUserRepository = Mockito.mock(UserRepository.class);
    useCase = new GetUserById(mockUserRepository);
  }

  @Test
  void shouldInheritUseCase() {
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldCallMethodGetFromTheRepository() {
    useCase.execute(userId);
    Mockito.verify(mockUserRepository).get(userId);
  }

  @Test
  void shouldGetUserFromTheRepository() throws ExecutionException, InterruptedException {
    Mockito
        .when(mockUserRepository.get(userId))
        .thenReturn(CompletableFuture.completedFuture(Either.right(user)));

    var result = useCase.execute(userId).get().get();

    assertThat(result).isEqualTo(user);
  }

  @Test
  void notExistsUser_shouldReturnNotFound() throws ExecutionException, InterruptedException {
    var failure = new NotFound();

    Mockito
        .when(mockUserRepository.get(userId))
        .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    var result = useCase.execute(userId).get().getLeft();

    assertThat(result).isEqualTo(failure);
  }


}