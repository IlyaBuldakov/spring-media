package com.htc.application.services;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Интерфейс, описывающий базовые операции для взаимодействия с пользователем.
 * Слой преобразования DTO <---> Domain entity.
 */
public interface UsersService {

  /**
   * Получение списка пользователей.
   *
   * @return Список пользователей.
   */
  CompletableFuture<List<UserResponse>> getAll(Collection<? extends GrantedAuthority> authorities);

  /**
   * Получение пользователя по идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @return {@link UserResponse Представление} пользователя.
   */
  CompletableFuture<UserResponse> getById(Collection<? extends GrantedAuthority> authorities,
                                          String id);

  /**
   * Получение пользователя по E-mail.
   *
   * @param email E-mail.
   * @return {@link UserResponse Представление} пользователя.
   */
  CompletableFuture<UserResponse> getByEmail(String email);

  /**
   * Создание пользователя.
   *
   * @param user {@link UserRequest Представление} пользователя (запрос).
   * @return void.
   */
  CompletableFuture<Void> create(Collection<? extends GrantedAuthority> authorities,
                                 UserRequest user);

  /**
   * Обновление пользователя.
   *
   * @param user {@link UserRequest Представление} пользователя (запрос).
   * @param id   Идентификатор пользователя.
   * @return void.
   */
  CompletableFuture<Void> update(Collection<? extends GrantedAuthority> authorities,
                                 UserRequest user,
                                 String id);

  /**
   * Удаление пользователя по идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @return void.
   */
  CompletableFuture<Void> delete(Collection<? extends GrantedAuthority> authorities,
                                 String id);
}
