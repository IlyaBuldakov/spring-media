package com.htc.domain.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteUserByIdTest {
  final UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
  final DeleteUserById useCase = new DeleteUserById(mockUserRepository);
  final DeleteUserById.Params params = new DeleteUserById.Params(
          new Random().nextLong(1, 32),
          "idKey"
  );

  @Test
  void shouldInheritUseCase() {
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldDeleteUserByTheRepository() {
    useCase.execute(params);
    Mockito.verify(mockUserRepository).delete(Id.create(params.id()).get());
  }

  @Test
  void userExists_ShouldDeleteUserAndReturnVoid() throws ExecutionException, InterruptedException {
    // Arrange
    Mockito.when(mockUserRepository.delete(Id.create(params.id()).get()))
            .thenReturn(EitherHelper.goodRight(null));
    // Act
    var result = useCase.execute(params).get().get();
    // Assert
    assertThat(result).isNull();
  }

  @Test
  void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    var failure = NotFound.DEFAULT_MESSAGE;
    Mockito.when(mockUserRepository.delete(Id.create(params.id()).get()))
            .thenReturn(EitherHelper.badLeft(failure));
    var result = useCase.execute(params).get().getLeft();
    assertThat(result).isEqualTo(failure);
  }
}
