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
class GetUserByIdTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final GetUserById useCase = new GetUserById(mockUserRepository);
  final GetUserById.Params params = new GetUserById.Params(
          Id.create(new Random().nextLong(1, 32)).get()
  );
  final AddUser.Params addParams = new AddUser.Params(
          UserName.create("name").get(),
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
  void shouldGetUserFromTheRepository() {
    useCase.execute(params);
    Mockito.verify(mockUserRepository).get(params.id());
  }

  @Test
  void userExists_ShouldReturnUser() throws ExecutionException, InterruptedException {
    // Arrange
    var userm = new UserModel(
            params.id().getValue(),
            addParams.name().getValue(),
            addParams.password().getValue(),
            addParams.email().getValue(),
            addParams.image().getValue(),
            addParams.role().getName());
    Mockito.when(mockUserRepository.get(params.id()))
            .thenReturn(EitherHelper.goodRight(userm));
    // Act
    var result = useCase.execute(params).get().get();
    // Assert
    assertThat(result).isEqualTo(userm);
  }

  @Test
  void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    var failure = NotFound.DEFAULT_MESSAGE;
    Mockito.when(mockUserRepository.get(params.id()))
            .thenReturn(EitherHelper.badLeft(failure));
    var result = useCase.execute(params).get().getLeft();
    assertThat(result).isEqualTo(failure);
  }
}
