package com.htc.domain.usecases.file;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteFileByIdTest {
  final FileRepository mockFileRepository = mock(FileRepository.class);
  final DeleteFileById useCase = new DeleteFileById(mockFileRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldDeleteFileByTheRepository() {
    // Arrange
    var fileId = new Random().nextInt();

    // Act
    useCase.execute(fileId);

    // Assert
    verify(mockFileRepository).delete(fileId);
  }

  @Test
  void fileExists_ShouldDeleteFileAndReturnVoid() throws ExecutionException, InterruptedException {
    // Arrange
    var fileId = new Random().nextInt();

    when(mockFileRepository.delete(fileId))
            .thenReturn(CompletableFuture.completedFuture(Either.right(null)));

    // Act
    var result = useCase.execute(fileId)
            .get()
            .get();

    // Assert
    assertThat(result).isNull();
  }

  @Test
  void fileDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    // Arrange
    var fileId = new Random().nextInt();
    var failure = new NotFound();

    when(mockFileRepository.delete(fileId))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(fileId)
            .get()
            .getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}
