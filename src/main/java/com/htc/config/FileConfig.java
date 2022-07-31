package com.htc.config;

import com.htc.domain.repositories.FileRepository;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.service.FileService;
import com.htc.domain.usecases.FileUseCase;
import com.htc.domain.util.FileMetadata;
import com.htc.domain.util.FileUploadService;
import com.htc.domain.util.impl.FileMetadataImpl;
import com.htc.domain.util.impl.FileUploadServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileConfig {
  @Bean
  public FileUseCase.Create createFile(
      FileRepository repository,
      UserRepository userRepository,
      FileMetadata fileMetadata,
      FileUploadService fileUploadService
  ) {
    return new FileUseCase.Create(
        repository,
        userRepository,
        fileMetadata,
        fileUploadService);
  }

  @Bean
  public FileUseCase.DeleteById deleteFileById(
      FileRepository repository,
      UserRepository userRepository
  ) {
    return new FileUseCase.DeleteById(repository, userRepository);
  }

  @Bean
  public FileUseCase.GetAllByTask getAllFilesByTask(
      FileRepository repository,
      UserRepository userRepository
  ) {
    return new FileUseCase.GetAllByTask(repository, userRepository);
  }

  @Bean
  public FileService fileService(
      FileUseCase.Create create,
      FileUseCase.DeleteById deleteById,
      FileUseCase.GetAllByTask getAllByTask) {
    return new FileService(
        create,
        deleteById,
        getAllByTask);
  }
}
