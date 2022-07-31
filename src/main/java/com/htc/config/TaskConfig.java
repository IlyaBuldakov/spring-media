package com.htc.config;

import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.service.TaskService;
import com.htc.domain.usecases.TaskUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfig {
  @Bean
  public TaskUseCase.Create createTask(
      TaskRepository repository,
      UserRepository userRepository
  ) {
    return new TaskUseCase.Create(repository, userRepository);
  }

  @Bean
  public TaskUseCase.Update updateTaskById(
      TaskRepository repository,
      UserRepository userRepository
  ) {
    return new TaskUseCase.Update(repository, userRepository);
  }

  @Bean
  public TaskUseCase.DeleteById deleteTaskById(
      TaskRepository repository,
      UserRepository userRepository
  ) {
    return new TaskUseCase.DeleteById(repository, userRepository);
  }

  @Bean
  public TaskUseCase.GetById getTaskById(
      TaskRepository repository,
      UserRepository userRepository
  ) {
    return new TaskUseCase.GetById(repository, userRepository);
  }

  @Bean
  public TaskUseCase.GetAll getAllTasks(
      TaskRepository repository,
      UserRepository userRepository
  ) {
    return new TaskUseCase.GetAll(repository, userRepository);
  }

  @Bean
  public TaskService taskService(
      TaskUseCase.Create create,
      TaskUseCase.DeleteById deleteById,
      TaskUseCase.GetAll getAll,
      TaskUseCase.GetById getById,
      TaskUseCase.Update update) {
    return new TaskService(
        create,
        deleteById,
        getAll,
        getById,
        update);
  }
}
