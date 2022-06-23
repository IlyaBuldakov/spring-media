package finalproject.application.controllers;

import finalproject.application.dto.failures.BadRequestDto;
import finalproject.application.dto.failures.FieldInvalidDto;
import finalproject.application.dto.failures.InternalServerErrorDto;
import finalproject.application.services.UserService;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

  UserService userservice;

  @GetMapping
  public List<User> getUsers() throws ExecutionException, InterruptedException {
    return userservice.getAllUsers().get().get();
  }
  @GetMapping("/createRandom")
  public User createRandom() throws ExecutionException, InterruptedException {
    return userservice.createNewUser(User.createRandomFakeUser()
            .getOrElseThrow(failure -> new BadRequestDto(failure, (FieldInvalidDto[]) Arrays
                    .stream(failure.getProblems())
                    .map(problem -> new FieldInvalidDto(problem))
                    .toArray())))
                    .get()
            .getOrElseThrow(failure -> new InternalServerErrorDto(failure));
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable int id) throws ExecutionException, InterruptedException {
    return userservice.getUserById(id).get().get();}

  @GetMapping("/deleteUser/{id}")
  public void deleteUser(@PathVariable int id) throws ExecutionException, InterruptedException {
    userservice.deleteUserById(id);
  }


}
