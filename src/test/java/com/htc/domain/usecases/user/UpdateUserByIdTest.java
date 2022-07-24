package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.utility.parameters.Id;
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
class UpdateUserByIdTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final UpdateUserById useCase = new UpdateUserById(mockUserRepository);
  final UpdateUserById.Params params = new UpdateUserById.Params(
          new Random().nextLong(1, 32), "idKey",
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
  void shouldUpdateUserByTheRepository() {
    // Act
    useCase.execute(params);
    // Assert
    Mockito.verify(mockUserRepository).update(
            Id.create(params.id()).get(),
            UserName.create(params.name()).get(),
            UserEmail.create(params.email()).get(),
            UserPassword.create(params.password()).get(),
            UserImage.create(params.image()).get(),
            params.role());
  }

  @Test
  void usersExist_ShouldUpdateUser() throws ExecutionException, InterruptedException {
    var userModel = new UserModel(
            params.id(),
            params.name(),
            params.password(),
            params.email(),
            params.image(),
            params.role().getName());
    Mockito.when(mockUserRepository.update(
                    Id.create(params.id()).get(),
                    UserName.create(params.name()).get(),
                    UserEmail.create(params.email()).get(),
                    UserPassword.create(params.password()).get(),
                    UserImage.create(params.image()).get(),
                    params.role()))
            .thenReturn(EitherHelper.goodRight(userModel));
    var result = useCase.execute(params).get().get();
    assertThat(result).isEqualTo(userModel);
  }

  @Test
  void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    var failure = NotFound.DEFAULT_MESSAGE;
    Mockito.when(mockUserRepository.update(
                    Id.create(params.id()).get(),
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
