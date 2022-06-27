package finalproject.application.controllers;

import finalproject.application.dto.failures.BadRequestDto;
import finalproject.application.dto.failures.FieldInvalidDto;
import finalproject.application.dto.failures.InternalServerErrorDto;
import finalproject.application.dto.user.UserDto;
import finalproject.application.dto.user.UserRequestDto;
import finalproject.application.services.UserService;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.Role;
import finalproject.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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

  @PutMapping
  public CompletableFuture<User> createUser(@RequestBody UserRequestDto userdto) {
    User user = User.create(userdto.getEmail(), userdto.getName(), userdto.getAvatar(), userdto.getPassword(), Role.getRoleById(userdto.getRole().getId()))
            .getOrElseThrow(failure -> new BadRequestDto(failure, Arrays.stream(failure.getProblems())
                    .map(problem -> new FieldInvalidDto(problem))
                    .toArray(FieldInvalidDto[]::new)));
    return userservice.createNewUser(user).thenApply(either -> either.getOrElseThrow(failure -> new InternalServerErrorDto(failure)));


  }


  @GetMapping("/createRandom")
  public CompletableFuture<User> createRandom() {
    return userservice.createNewUser(User.createRandomFakeUser()
            .getOrElseThrow(failure -> new BadRequestDto(failure, Arrays.stream(failure.getProblems())
                    .map(problem -> new FieldInvalidDto(problem))
                    .toArray(FieldInvalidDto[]::new))))
            .thenApply(either -> either.getOrElseThrow(failure -> new InternalServerErrorDto(failure)));


  }

  @GetMapping("/{id}")
  public CompletableFuture<User> getUser(@PathVariable int id) throws ExecutionException, InterruptedException {
    return userservice.getUserById(id).thenApply(either -> either.get());}

  @GetMapping("/deleteUser/{id}")
  public CompletableFuture<Void> deleteUser(@PathVariable int id) {
    return userservice.deleteUserById(id).thenApply(either -> either.getOrElseThrow(failure -> new InternalServerErrorDto(failure)));
  }


}
