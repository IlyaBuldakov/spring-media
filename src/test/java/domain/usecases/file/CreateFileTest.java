package domain.usecases.file;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import domain.entities.failures.AlreadyExists;
import domain.entities.files.File;
import domain.repositories.FileRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateFileTest {

  final FileRepository mockFileRepository = mock(FileRepository.class);

  final CreateFile useCase = new CreateFile(mockFileRepository);

  @Test
  void shouldInheritUseCase() {
    // Assert
    assertThat(useCase).isInstanceOf(UseCase.class);
  }

  @Test
  void shouldCreateFileByTheRepository() {
    //Arrange
    var file = File.createTestFile();

    // Act
    useCase.execute(file);

    // Assert
    verify(mockFileRepository).create(file);
  }

  @Test
  void fileNotExist_ShouldCreateFileAndReturnFile()
          throws ExecutionException, InterruptedException {
    // Arrange
    var file = File.createTestFile();
    when(mockFileRepository.create(file))
            .thenReturn(CompletableFuture.completedFuture(Either.right(file)));

    // Act
    var result = useCase.execute(file)
            .get()
            .get();

    // Assert
    assertThat(result).isEqualTo(file);
  }

  @Test
  void fileExist_ShouldReturnAlreadyExists()
          throws ExecutionException, InterruptedException {
    // Arrange
    var file = File.createTestFile();
    var failure = new AlreadyExists();

    when(mockFileRepository.create(file))
            .thenReturn(CompletableFuture.completedFuture(Either.left(failure)));

    // Act
    var result = useCase.execute(file)
            .get()
            .getLeft();

    // Assert
    assertThat(result).isEqualTo(failure);
  }
}