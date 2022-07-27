package com.htc.config;

import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.user.AddUser;
import com.htc.domain.usecases.user.DeleteUserById;
import com.htc.domain.usecases.user.GetAllUsers;
import com.htc.domain.usecases.user.GetUserByEmail;
import com.htc.domain.usecases.user.GetUserById;
import com.htc.domain.usecases.user.UpdateUserById;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Инициализация сценариев использования сущности пользователя.
 */
@Configuration
public class UserConfig {
  @Bean
  public AddUser addUser(UserRepository repository) {
    return new AddUser(repository);
  }

  @Bean
  public GetUserById getUserById(UserRepository repository) {
    return new GetUserById(repository);
  }

  @Bean
  public GetUserByEmail getUserByEmail(UserRepository repository) {
    return new GetUserByEmail(repository);
  }

  @Bean
  public GetAllUsers getAllUsers(UserRepository repository) {
    return new GetAllUsers(repository);
  }

  @Bean
  public UpdateUserById updateUserById(UserRepository repository) {
    return new UpdateUserById(repository);
  }

  @Bean
  public DeleteUserById deleteUserById(UserRepository repository) {
    return new DeleteUserById(repository);
  }
}
