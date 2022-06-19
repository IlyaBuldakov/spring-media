package com.htc.domain.usecases.file;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.files.File;
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
class GetFileByIdTest {
  final FileRepository mockFileRepository = mock(FileRepository.class);
  final GetFileById useCase = new GetFileById(mockFileRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldGetFileFromTheRepository() {
    // Arrange
    var fileId = new Random().nextInt();

    // Act
    useCase.execute(fileId);

    // Assert
    verify(mockFileRepository).get(fileId);
  }

  @Test
  void fileExists_ShouldReturnFile() throws ExecutionException, InterruptedException {
    // Arrange
    var fileId = new Random().nextInt();
    var task = File.createTestFile(fileId);

    when(mockFileRepository.get(fileId))
            .thenReturn(CompletableFuture.completedFuture(Either.right(task)));

    // Act
    var result = useCase.execute(fileId)
            .get()
            .get();

    // Assert
    assertThat(result).isEqualTo(task);
  }

  @Test
  void fileDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    // Arrange
    var fileId = new Random().nextInt();
    var failure = NotFound.DEFAULT_MESSAGE;

    when(mockFileRepository.get(fileId))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(fileId)
            .get()
            .getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}
