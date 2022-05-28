package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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

@ExtendWith(MockitoExtension.class)
class DeleteUserByIdTest {

    UserRepository mockUserRepository = mock(UserRepository.class);
    DeleteUserById useCase = new DeleteUserById(mockUserRepository);

    @Test
    void shouldInheritUseCase() {
        assertThat(useCase).isInstanceOf(UseCase.class);
    }

    @Test
    void shouldDeleteUserFromRepository() {
        var userId = new Random().nextInt(255);

        useCase.execute(userId);

        verify(mockUserRepository).delete(userId);
    }

    @Test
    void userExists_shouldReturnVoid() throws ExecutionException, InterruptedException {
        var userId = new Random().nextInt(255);

        when(mockUserRepository.delete(userId))
                .thenReturn(CompletableFuture.completedFuture(Either.right(null)));

        var result = useCase.execute(userId)
                .get()
                .get();

        assertThat(result).isNull();
    }

    @Test
    void userDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
        var userId = new Random().nextInt(255);

        when(mockUserRepository.delete(userId))
                .thenReturn(CompletableFuture.completedFuture(Either.left(new NotFound(""))));

        var result = useCase.execute(userId)
                .get()
                .getLeft();

        assertThat(result).isInstanceOf(NotFound.class);
    }
}