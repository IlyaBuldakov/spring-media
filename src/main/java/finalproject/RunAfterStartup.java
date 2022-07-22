package finalproject;

import finalproject.application.services.UserService;
import finalproject.domain.entities.user.Role;
import finalproject.domain.entities.user.User;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Класс автозагрузки после запуска прилжения.
 */
@AllArgsConstructor
@Component
public class RunAfterStartup {

  private UserService userService;
  static final String INIT_ADMIN_LOGIN = "admin@admin.admin";
  static final String INIT_ADMIN_PASSWORD = "123456789aA";

  /**
   * Создание пользователя, создающийся при отсутствии пользователей при развертывании приложения.
   *
   * @throws ExecutionException Стандартное исключение асинхронных методов
   * @throws InterruptedException Стандартное исключение асинхронных методов
   */
  @EventListener(ApplicationReadyEvent.class)
  public void runAfterStartup() throws ExecutionException, InterruptedException {
    if (userService.getAllUsers().get().get().size() == 0) {
      User user = User.create(INIT_ADMIN_LOGIN, "admin", "", INIT_ADMIN_PASSWORD, Role.ADMIN).get();
      userService.createNewUser(user);
    }

  }


}
