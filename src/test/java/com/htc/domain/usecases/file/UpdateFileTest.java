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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateFileTest {
  final FileRepository mockFileRepository = mock(FileRepository.class);
  final UpdateFile useCase = new UpdateFile(mockFileRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldUpdateFileByTheRepository() {
    // Arrange
    var file = File.createTestFile();

    // Act
    useCase.execute(file);

    // Assert
    verify(mockFileRepository).update(file);
  }

  @Test
  void fileExist_ShouldUpdateUserAndReturnFile() throws ExecutionException, InterruptedException {
    // Arrange
    var file = File.createTestFile();

    when(mockFileRepository.update(file))
            .thenReturn(CompletableFuture.completedFuture(Either.right(file)));

    // Act
    var result = useCase.execute(file)
            .get()
            .get();

    // Assert
    assertThat(result).isEqualTo(file);
  }

  @Test
  void fileNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
    // Arrange
    var file = File.createTestFile();
    var failure = new NotFound();

    when(mockFileRepository.update(file))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(file)
            .get()
            .getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}
