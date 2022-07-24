package com.htc.config;

import com.htc.domain.repositories.FileRepository;
import com.htc.domain.usecases.file.DeleteFileById;
import com.htc.domain.usecases.file.GetFileById;
import com.htc.domain.usecases.file.UploadFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Инициализация сценариев использования сущности файла.
 */
@Configuration
public class FileConfig {
  @Bean
  public UploadFile uploadFile(FileRepository repository) {
    return new UploadFile(repository);
  }

  @Bean
  public GetFileById getFileById(FileRepository repository) {
    return new GetFileById(repository);
  }

  @Bean
  public DeleteFileById deleteFileById(FileRepository repository) {
    return new DeleteFileById(repository);
  }
}
