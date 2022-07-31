package com.htc.infrastructure.repositories;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.repositories.TaskRepository;
import com.htc.infrastructure.models.TaskModel;
import com.htc.infrastructure.models.UserModel;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория пользователей.
 */
@Repository
@AllArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

  Tasks tasks;

  UserRepositoryImpl userRepository;

  @Override
  public Task create(
      Task.Name name,
      Content.Type contentType,
      Task.Description description,
      User author,
      User executor,
      LocalDateTime dateCreated,
      LocalDateTime dateExpired,
      Task.Status status
  ) {

    var authorModel = userRepository.users.findById(author.id().getValue());
    var executorModel = userRepository.users.findById(executor.id().getValue());
    var task = new TaskModel(
        name.getValue(),
        contentType,
        description.getValue(),
        authorModel.get(),
        executorModel.get(),
        LocalDateTime.now(),
        dateExpired,
        Task.Status.IN_WORK);
    tasks.save(task);
    return task.toEntity();
  }

  @Override
  public Task update(
      Id id,
      Task.Name name,
      Content.Type contentType,
      Task.Description description,
      User author,
      User executor,
      LocalDateTime dateExpired
  ) {

    var taskModel = this.get(id).get();
    var authorModel = userRepository.users.findById(author.id().getValue());
    var executorModel = userRepository.users.findById(executor.id().getValue());
    var task = new TaskModel(
        id.getValue(),
        name.getValue(),
        contentType,
        description.getValue(),
        authorModel.get(),
        executorModel.get(),
        taskModel.dateCreated(),
        dateExpired,
        taskModel.status());
    task = tasks.save(task);
    return task.toEntity();
  }

  @Override
  public void delete(Id id) {
    tasks.deleteById(id.getValue());
  }

  @Override
  public Optional<Task> get(Id id) {
    var task = tasks.findById(id.getValue());
    return tasks
        .findById(id.getValue())
        .map(TaskModel::toEntity);
  }

  @Override
  public Collection<Task> getAll() {
    return tasks
        .findAll()
        .stream()
        .map(TaskModel::toEntity)
        .collect(Collectors.toList());
  }

  @Override
  public boolean exists(Id id) {
    return tasks.existsById(id.getValue());
  }

  /**
   * ORM для доступа к данным пользователей в СУБД.
   */
  interface Tasks extends JpaRepository<TaskModel, Integer> {
  }
}