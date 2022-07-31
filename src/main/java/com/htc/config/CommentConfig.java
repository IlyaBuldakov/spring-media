package com.htc.config;

import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.service.CommentService;
import com.htc.domain.usecases.CommentUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentConfig {
  @Bean
  public CommentUseCase.Create createComment(
      CommentRepository repository,
      UserRepository userRepository
  ) {
    return new CommentUseCase.Create(repository, userRepository);
  }

  @Bean
  public CommentService commentService(
      CommentUseCase.Create create
  ) {
    return new CommentService(
        create
    );
  }
}
