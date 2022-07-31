package com.htc.config;

import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.service.ContentService;
import com.htc.domain.usecases.ContentUseCase;
import com.htc.domain.util.FileMetadata;
import com.htc.domain.util.FileUploadService;
import com.htc.domain.util.PreviewPicture;
import com.htc.domain.util.impl.FileMetadataImpl;
import com.htc.domain.util.impl.FileUploadServiceImpl;
import com.htc.domain.util.impl.PreviewPictureImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContentConfig {
  @Bean
  public ContentUseCase.Create createContent(
      ContentRepository repository,
      UserRepository userRepository,
      FileMetadata fileMetadata,
      FileUploadService fileUploadService,
      PreviewPicture previewPicture
  ) {
    return new ContentUseCase.Create(
        userRepository,
        repository,
        fileMetadata,
        fileUploadService,
        previewPicture);
  }

  @Bean
  public ContentUseCase.DeleteById deleteContentById(
      ContentRepository repository,
      UserRepository userRepository
  ) {
    return new ContentUseCase.DeleteById(repository, userRepository);
  }

  @Bean
  public ContentUseCase.GetFeed getContentFeed(
      ContentRepository repository,
      UserRepository userRepository
  ) {
    return new ContentUseCase.GetFeed(repository, userRepository);
  }

  @Bean
  public FileMetadata fileMetadata() {
    return new FileMetadataImpl();
  }

  @Bean
  public FileUploadService fileUploadService() {
    return new FileUploadServiceImpl();
  }

  @Bean
  public PreviewPicture previewPicture() {
    return new PreviewPictureImpl();
  }

  @Bean
  public ContentService contentService(
      ContentUseCase.Create create,
      ContentUseCase.DeleteById deleteById,
      ContentUseCase.GetFeed getFeed) {
    return new ContentService(
        create,
        deleteById,
        getFeed);
  }
}