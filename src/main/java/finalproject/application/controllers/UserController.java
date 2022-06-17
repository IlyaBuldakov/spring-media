package finalproject.application.controllers;

import finalproject.application.services.UserService;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    return userservice.getAllUsers().get().getOrElseThrow(()-> new Failure("Чёт не то"));
  }
  @GetMapping("/createRandom")
  public User createRandom() throws ExecutionException, InterruptedException, Failure {
    return userservice.createNewUser(User.createRandomFakeUser()).get().getOrElseThrow(()-> new Failure("Опять чёт не то"));
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable int id) throws ExecutionException, InterruptedException, Failure {
    return userservice.getUserById(id).get().get();}

  @GetMapping("/deleteUser/{id}")
  public void deleteUser(@PathVariable int id) throws ExecutionException, InterruptedException, Failure {
    userservice.deleteUserById(id);
  }


}
