package com.htc.config;

import com.htc.domain.repositories.CommentRepository;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.task.AddTask;
import com.htc.domain.usecases.task.DeleteTaskById;
import com.htc.domain.usecases.task.GetAllTasks;
import com.htc.domain.usecases.task.GetTaskById;
import com.htc.domain.usecases.task.UpdateTaskById;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Инициализация сценариев использования сущности задачи.
 */
@Configuration
public class TaskConfig {
  @Bean
  public AddTask addTask(TaskRepository repository, UserRepository userRepository) {
    return new AddTask(repository, userRepository);
  }

  @Bean
  public GetTaskById getTaskById(TaskRepository repository) {
    return new GetTaskById(repository);
  }

  @Bean
  public GetAllTasks getAllTasks(TaskRepository repository) {
    return new GetAllTasks(repository);
  }

  @Bean
  public UpdateTaskById updateTaskById(TaskRepository repository,
                                       UserRepository userRepository,
                                       FileRepository fileRepository,
                                       ContentRepository contentRepository,
                                       CommentRepository commentRepository) {
    return new UpdateTaskById(repository, userRepository,
            fileRepository, contentRepository, commentRepository);
  }

  @Bean
  public DeleteTaskById deleteTaskById(TaskRepository repository) {
    return new DeleteTaskById(repository);
  }
}
