package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.EntityName;
import com.htc.domain.entities.utility.parameters.user.UserEmail;
import com.htc.domain.entities.utility.parameters.user.UserImage;
import com.htc.domain.entities.utility.parameters.user.UserPassword;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.infrastructure.models.user.UserModel;
import com.htc.utility.EitherHelper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllUsersTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final GetAllUsers useCase = new GetAllUsers(mockUserRepository);
  final AddUser.Params params = new AddUser.Params(
          EntityName.create("name").get(),
          UserEmail.create("email@email.com").get(),
          UserPassword.create("password11AA").get(),
          UserImage.create("image==").get(),
          Role.ADMIN
  );

  @Test
  void shouldInheritUseCase() {
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldGetAllUsersFromTheRepository() {
    useCase.execute(null);
    Mockito.verify(mockUserRepository).getAll();
  }

  @Test
  void usersExist_ShouldReturnAllUsers() throws ExecutionException, InterruptedException {
    var usersm = List.of(
            (User) new UserModel(
                    new Random().nextLong(1, 32),
                    params.name().getValue(),
                    params.password().getValue(),
                    params.email().getValue(),
                    params.image().getValue(),
                    params.role().getName())
    );
    Mockito.when(mockUserRepository.getAll()).thenReturn(EitherHelper.goodRight(usersm));
    var result = useCase.execute(null).get().get();
    assertThat(result).isEqualTo(usersm);
  }

  @Test
  void usersNotExist_ShouldReturnEmptyList() throws ExecutionException, InterruptedException {
    var users = List.<User>of();
    Mockito.when(mockUserRepository.getAll()).thenReturn(EitherHelper.goodRight(users));
    var result = useCase.execute(null).get().get();
    assertThat(result).isEqualTo(users);
  }
}
