package com.htc.config;

import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.content.AddContent;
import com.htc.domain.usecases.content.ChangeContentById;
import com.htc.domain.usecases.content.DeleteContentById;
import com.htc.domain.usecases.content.GetAllContent;
import com.htc.domain.usecases.content.GetContentById;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Инициализация сценариев использования сущности контента.
 */
@Configuration
public class ContentConfig {
  @Bean
  public AddContent addContent(ContentRepository repository,
                               FileRepository fileRepository,
                               TaskRepository taskRepository) {
    return new AddContent(repository, fileRepository, taskRepository);
  }

  @Bean
  public GetContentById getContentById(ContentRepository repository) {
    return new GetContentById(repository);
  }

  @Bean
  public GetAllContent getAllContent(ContentRepository repository) {
    return new GetAllContent(repository);
  }

  @Bean
  public ChangeContentById changeContentById(ContentRepository repository,
                                             FileRepository fileRepository,
                                             TaskRepository taskRepository) {
    return new ChangeContentById(repository, fileRepository, taskRepository);
  }

  @Bean
  public DeleteContentById deleteContentById(ContentRepository repository) {
    return new DeleteContentById(repository);
  }
}
