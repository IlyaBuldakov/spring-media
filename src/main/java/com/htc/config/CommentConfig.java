package com.htc.config;

import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.comment.AddComment;
import com.htc.domain.usecases.comment.DeleteCommentById;
import com.htc.domain.usecases.comment.GetCommentById;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Инициализация сценариев использования сущности комментария.
 */
@Configuration
public class CommentConfig {
  @Bean
  public AddComment addComment(CommentRepository repository,
                               UserRepository userRepository,
                               TaskRepository taskRepository) {
    return new AddComment(repository, userRepository, taskRepository);
  }

  @Bean
  public GetCommentById getCommentById(CommentRepository repository) {
    return new GetCommentById(repository);
  }

  @Bean
  public DeleteCommentById deleteCommentById(CommentRepository repository) {
    return new DeleteCommentById(repository);
  }
}
