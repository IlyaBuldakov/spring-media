package com.htc.application.services;

import com.htc.application.dto.task.TaskRequest;
import com.htc.application.dto.task.TaskResponse;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.security.core.GrantedAuthority;

/**
 * Интерфейс, описывающий базовые операции для взаимодействия с задачами.
 * Слой преобразования DTO <---> Domain entity.
 */
public interface TasksService {

  /**
   * Получение списка задач.
   *
   * @return Список задач.
   */
  CompletableFuture<List<TaskResponse>> getAll(
          Collection<? extends GrantedAuthority> authorities);

  /**
   * Получение задачи по идентификатору.
   *
   * @param id Идентификатор задачи.
   * @return {@link TaskResponse Представление} задачи.
   */
  CompletableFuture<TaskResponse> getById(
          Collection<? extends GrantedAuthority> authorities,
          String id);

  /**
   * Создание задачи.
   *
   * @param task {@link TaskRequest Представление} задачи (запрос).
   * @return void.
   */
  CompletableFuture<Void> create(
          Collection<? extends GrantedAuthority> authorities,
          TaskRequest task);

  /**
   * Обновление задачи.
   *
   * @param task {@link TaskRequest Представление} задачи (запрос).
   * @param id   Идентификатор задачи.
   * @return void.
   */
  CompletableFuture<Void> update(
          Collection<? extends GrantedAuthority> authorities,
          TaskRequest task, String id);

  /**
   * Удаление задачи по идентификатору.
   *
   * @param id Идентификатор задачи.
   * @return void.
   */
  CompletableFuture<Void> delete(
          Collection<? extends GrantedAuthority> authorities,
          String id);
}
