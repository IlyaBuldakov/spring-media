package finalproject.application.services.impl;

import finalproject.application.services.NotificationService;
import finalproject.domain.entities.notifications.Notification;
import finalproject.domain.entities.user.Role;
import finalproject.domain.entities.user.User;
import finalproject.infrastructure.repositories.NotificationsRepository;
import finalproject.infrastructure.repositories.TaskRepository;
import finalproject.infrastructure.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

  private NotificationsRepository notificationsRepository;
  private UserRepository userRepository;
  private TaskRepository taskRepository;


  @Override
  public List<Notification> getNotifications(int userId) {
    User authorizedUser = userRepository.findById(userId).get();
    var notifications = notificationsRepository.findAll();
    if (authorizedUser.getRole() == Role.ADMIN) {
      return notifications;
    }

   Set<Integer> relatedTasks = authorizedUser.getTasksAsAuthor()
           .stream().map(task -> task.getId()).collect(Collectors.toSet());
    Set<Integer> relatedTasks2 = authorizedUser.getTasksAsContentMaker()
            .stream().map(task -> task.getId()).collect(Collectors.toSet());
    relatedTasks.addAll(relatedTasks2);
    var notes = notifications.stream()
            .filter(note -> {
              for (Integer i : relatedTasks) {
                if (note.getTaskId() == (int) i)
                {return true;}
              }
              return false;
            }).collect(Collectors.toList());
    return notes;




  }

}
