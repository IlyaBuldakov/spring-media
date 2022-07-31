package finalproject.application.services;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.notifications.Notification;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface NotificationService {
  List<Notification> getNotifications(int userId);
}
