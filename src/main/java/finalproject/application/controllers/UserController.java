package finalproject.application.controllers;

import finalproject.application.services.UserService;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

  UserService userservice;

  @GetMapping
  public List<User> getUsers() throws ExecutionException, InterruptedException, Failure {
    userservice.createNewUser(User.createRandomFakeUser());
    userservice.createNewUser(User.createRandomFakeUser());
    userservice.createNewUser(User.createRandomFakeUser());
    return userservice.getAllUsers().get().getOrElseThrow(()-> new Failure("Чёт не то"));
  }



}
