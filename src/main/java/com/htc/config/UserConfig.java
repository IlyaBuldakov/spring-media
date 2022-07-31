package com.htc.config;

import com.htc.domain.repositories.UserRepository;
import com.htc.domain.service.UserService;
import com.htc.domain.usecases.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
  @Bean
  public UserUseCase.Create createUser(UserRepository repository) {
    return new UserUseCase.Create(repository);
  }

  @Bean
  public UserUseCase.UpdateById updateUserById(UserRepository userRepository) {
    return new UserUseCase.UpdateById(userRepository);
  }

  @Bean
  public UserUseCase.DeleteById deleteUserById(UserRepository userRepository) {
    return new UserUseCase.DeleteById(userRepository);
  }

  @Bean
  public UserUseCase.GetById getUserById(UserRepository userRepository) {
    return new UserUseCase.GetById(userRepository);
  }

  @Bean
  public UserUseCase.GetByEmail getUserByEmail(UserRepository userRepository) {
    return new UserUseCase.GetByEmail(userRepository);
  }

  @Bean
  public UserUseCase.GetAll getAllUsers(UserRepository userRepository) {
    return new UserUseCase.GetAll(userRepository);
  }

  @Bean
  public UserService userService(
      UserUseCase.Create create,
      UserUseCase.UpdateById updateById,
      UserUseCase.DeleteById deleteById,
      UserUseCase.GetById getById,
      UserUseCase.GetByEmail getByEmail,
      UserUseCase.GetAll getAll) {
    return new UserService(
        create,
        updateById,
        deleteById,
        getById,
        getByEmail,
        getAll);
  }
}