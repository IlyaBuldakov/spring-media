package com.htc.application.services;

import com.htc.application.dto.task.TaskRequest;
import com.htc.application.dto.task.TaskResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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
  CompletableFuture<List<TaskResponse>> getAll();

  /**
   * Получение задачи по идентификатору.
   *
   * @param id Идентификатор задачи.
   * @return {@link TaskResponse Представление} задачи.
   */
  CompletableFuture<TaskResponse> getById(String id);

  /**
   * Создание задачи.
   *
   * @param task {@link TaskRequest Представление} задачи (запрос).
   * @return void.
   */
  CompletableFuture<Void> create(TaskRequest task);

  /**
   * Обновление задачи.
   *
   * @param task {@link TaskRequest Представление} задачи (запрос).
   * @param id   Идентификатор задачи.
   * @return void.
   */
  CompletableFuture<Void> update(TaskRequest task, String id);

  /**
   * Удаление задачи по идентификатору.
   *
   * @param id Идентификатор задачи.
   * @return void.
   */
  CompletableFuture<Void> delete(String id);
}
