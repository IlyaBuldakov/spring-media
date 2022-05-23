package domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.entities.user.User;
import domain.entities.user.UserRole;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.collection.List;
import io.vavr.control.Either;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class GetAllUsersTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final GetAllUsers useCase = new GetAllUsers(mockUserRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldGetUserFromTheRepository() {
    // Act
    useCase.execute(null);

    // Assert
    Mockito.verify(mockUserRepository).getAll();
  }

  @Test
  void usersExist_ShouldReturnAllUsers() throws ExecutionException, InterruptedException {
    // Arrange
    var  users = List.of(
      new User(
          new Random().nextInt(),
          "user@example.com",
          "Passw0rd!",
          "Иванов Иван",
          new byte[] {},
          new UserRole(1, UserRole.RoleType.ADMIN)
      )
    );

    Mockito.when(mockUserRepository.getAll())
            .thenReturn(CompletableFuture.completedFuture(Either.right(users)));

    // Act
    var result = useCase.execute(null)
            .get()
            .get();

    // Assert
    assertThat(result).isEqualTo(users);
  }

  @Test
  void usersNotExist_ShouldReturnEmptyList() throws ExecutionException, InterruptedException {
    // Arrange
    var  users = List.<User>of();

    Mockito.when(mockUserRepository.getAll())
            .thenReturn(CompletableFuture.completedFuture(Either.right(users)));

    // Act
    var result = useCase.execute(null)
            .get()
            .get();

    // Assert
    assertThat(result).isEqualTo(users);
  }
}