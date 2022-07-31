package com.htc.infrastructure.repositories;

import com.htc.domain.entities.Notification;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.infrastructure.models.NotificationModel;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория уведомлений.
 */
@Repository
@AllArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {

  Notifications notifications;

  UserRepositoryImpl userRepository;

  TaskRepositoryImpl taskRepository;

  @Override
  public Notification create(
      Notification.Type type,
      LocalDateTime date,
      Notification.Message message,
      User user,
      Task task) {
    var userModel = userRepository.users.findById(user.id().getValue());
    var taskModel = taskRepository.tasks.findById(task.id().getValue());

    var notification = new NotificationModel(
        type,
        date,
        message.getValue(),
        userModel.get(),
        taskModel.get());
    notification = notifications.save(notification);
    return notification.toEntity();
  }

  @Override
  public Optional<Notification> get(Id id) {
    return null;
  }

  @Override
  public Collection<Notification> getAll() {
    return null;
  }

  @Override
  public void delete(Id id) {
    notifications.deleteById(id.getValue());
  }

  @Override
  public boolean exists(Id id) {
    return notifications.existsById(id.getValue());
  }

  /**
   * ORM для доступа к данным уведомлений в СУБД.
   */
  public interface Notifications extends JpaRepository<NotificationModel, Integer> {

  }
}
