package finalproject;

import finalproject.application.services.UserService;
import finalproject.domain.entities.user.Role;
import finalproject.domain.entities.user.User;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@Component
public class RunAfterStartup {

  private UserService userService;
  final String INIT_ADMIN_LOGIN = "admin@admin.admin";
  final String INIT_ADMIN_PASSWORD = "123456789aA";

  @EventListener(ApplicationReadyEvent.class)
  public void runAfterStartup() throws ExecutionException, InterruptedException {
    if (userService.getAllUsers().get().get().size() == 0)
      {
      User user = User.create(INIT_ADMIN_LOGIN, "admin", "", INIT_ADMIN_PASSWORD, Role.ADMIN).get();
      userService.createNewUser(user);
    }

  }


  }
