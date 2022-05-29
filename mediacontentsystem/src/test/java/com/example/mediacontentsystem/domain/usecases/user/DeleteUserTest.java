package com.example.mediacontentsystem.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.mediacontentsystem.domain.entities.failures.NotFound;
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
class DeleteUserTest {
  static UserRepository mockUserRepository;
  static DeleteUserById useCase;

  @BeforeAll
  static void setUp() {
    mockUserRepository = Mockito.mock(UserRepository.class);
    useCase = new DeleteUserById(mockUserRepository);
  }

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCaseUsingParams.class);
  }

  @Test
  void shouldCallMethodDeleteFromTheRepository() {
    // Act
    useCase.execute(1);

    // Assert
    Mockito.verify(mockUserRepository).delete(1);
  }

  @Test
  void shouldDeleteUser_shouldReturnVoid() throws ExecutionException, InterruptedException {
    // Arrange
    Mockito
        .when(mockUserRepository.delete(1))
        .thenReturn(CompletableFuture.completedFuture(Either.right(null)));

    // Act
    var result = useCase.execute(1).get().get();

    // Assert
    assertThat(result).isNull();
  }

  @Test
  void deleteNotExistsUser_shouldReturnNotFound()
      throws ExecutionException, InterruptedException {
    // Arrange
    var failure = new NotFound();
    Mockito
        .when(mockUserRepository.delete(2))
        .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(2).get().getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}
