package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * @author IlyaBuldakov
 */
public class DeleteUserByIdTest {

    final UsersRepository mockUsersRepository = mock(UsersRepository.class);
    final DeleteUserById useCase = new DeleteUserById(mockUsersRepository);
    @Test
    void shouldInheritUseCase() {
        assertThat(useCase).isInstanceOf(UseCase.class);
    }

    @Test
    void shouldDeleteUserFromRepository() {
        var userId = String.valueOf(new Random().nextInt(255));

        useCase.execute(userId);

        verify(mockUsersRepository).deleteById(Integer.parseInt(userId));
    }

    @Test
    void userExists_shouldReturnVoid() throws ExecutionException, InterruptedException {
        var userId = String.valueOf(new Random().nextInt(255));

        when(mockUsersRepository.deleteById(Integer.parseInt(userId)))
                .thenReturn(CompletableFuture.completedFuture(Either.right(null)));

        var result = useCase.execute(userId)
                .get()
                .get();

        assertThat(result).isNull();
    }

    @Test
    void userDoesNotExist_shouldReturnNotFound() throws ExecutionException, InterruptedException {
        var userId = String.valueOf(new Random().nextInt(255));

        when(mockUsersRepository.deleteById(Integer.parseInt(userId)))
                .thenReturn(CompletableFuture.completedFuture(Either.left(NotFound.USER)));

        var result = useCase.execute(userId)
                .get()
                .getLeft();

        assertThat(result).isInstanceOf(NotFound.class);
    }
}