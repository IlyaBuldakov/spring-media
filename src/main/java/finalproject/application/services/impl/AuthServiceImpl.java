package finalproject.application.services.impl;

import finalproject.application.services.AuthService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * реализация интерфейса.
 */
@Component
public class AuthServiceImpl implements AuthService {

  @Override
  public int getId() {
    return (Integer) SecurityContextHolder.getContext().getAuthentication().getDetails();
  }
}
