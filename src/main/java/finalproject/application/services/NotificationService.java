package finalproject.application.services;

import finalproject.domain.entities.notifications.Notification;
import java.util.List;

/**
 * сервис уведомлений.
 */
public interface NotificationService {
  List<Notification> getNotifications(int userId);
}
