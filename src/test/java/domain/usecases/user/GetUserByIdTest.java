package domain.usecases.user;

import domain.entities.user.User;
import domain.entities.user.UserRole;
import domain.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserByIdTest {
  final int existentUserId = 1;
  final int nonExistentUserId = -1;
  final User user = new User(
          existentUserId,
          "user@example.com",
          "Passw0rd!",
          "Иванов Иван",
          new byte[] {},
          new UserRole(1, UserRole.RoleType.ADMIN)
  );

  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final GetUserById useCase = new GetUserById(mockUserRepository);

  @Test
   void shouldGetUserFromTheRepository() {
    useCase.execute(existentUserId);
    Mockito.verify(mockUserRepository).get(existentUserId);
  }

  @Test
  void userExists_ShouldReturnUser() {
    throw new UnsupportedOperationException();
  }

  @Test
  void userDoesNotExist_ShouldReturnUserNotFound () {
    throw new UnsupportedOperationException();
  }
}