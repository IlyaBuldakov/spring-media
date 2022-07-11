package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.failures.AlreadyExists;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.utility.parameters.user.UserEmail;
import com.htc.domain.entities.utility.parameters.user.UserImage;
import com.htc.domain.entities.utility.parameters.user.UserName;
import com.htc.domain.entities.utility.parameters.user.UserPassword;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.infrastructure.models.user.UserModel;
import com.htc.utility.EitherHelper;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddUserTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final AddUser useCase = new AddUser(mockUserRepository);
  final AddUser.Params params = new AddUser.Params(
          "name", "nameKey",
          "email@email.com", "emailKey",
          "password11AA", "passwordKey",
          "image==", "imageKey",
          Role.ADMIN, "roleKey"
  );

  @Test
  void shouldInheritUseCase() {
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldCreateUserByTheRepository() {
    // Act
    useCase.execute(params);
    // Assert
    Mockito.verify(mockUserRepository).add(
            UserName.create(params.name()).get(),
            UserEmail.create(params.email()).get(),
            UserPassword.create(params.password()).get(),
            UserImage.create(params.image()).get(),
            params.role()
    );
  }

  @Test
  void userDoesNotExist_ShouldCreateUserAndReturnUser()
          throws ExecutionException, InterruptedException {
    var userm = new UserModel(
            new Random().nextLong(1, 32),
            params.name(),
            params.password(),
            params.email(),
            params.image(),
            params.role().getName());
    Mockito.when(mockUserRepository.add(
                    UserName.create(params.name()).get(),
                    UserEmail.create(params.email()).get(),
                    UserPassword.create(params.password()).get(),
                    UserImage.create(params.image()).get(),
                    params.role()))
            .thenReturn(EitherHelper.goodRight(userm));
    var result = useCase.execute(params).get().get();
    assertThat(result).isEqualTo(userm);
  }

  @Test
  void usersExist_ShouldReturnAlreadyExists() throws ExecutionException, InterruptedException {
    var failure = AlreadyExists.DEFAULT_MESSAGE;
    Mockito.when(mockUserRepository.add(
                    UserName.create(params.name()).get(),
                    UserEmail.create(params.email()).get(),
                    UserPassword.create(params.password()).get(),
                    UserImage.create(params.image()).get(),
                    params.role()))
            .thenReturn(EitherHelper.badLeft(failure));
    var result = useCase.execute(params).get().getLeft();
    assertThat(result).isEqualTo(failure);
  }
}
