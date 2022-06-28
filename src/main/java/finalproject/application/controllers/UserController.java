package finalproject.application.controllers;

import finalproject.application.dto.failures.BadRequestDto;
import finalproject.application.dto.failures.FieldInvalidDto;
import finalproject.application.dto.failures.InternalServerErrorDto;
import finalproject.application.dto.user.UserDto;
import finalproject.application.dto.user.UserRequestDto;
import finalproject.application.services.UserService;
import finalproject.domain.entities.user.Role;
import finalproject.domain.entities.user.User;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
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
  public CompletableFuture<UserDto> createUser(@RequestBody UserRequestDto userdata) {
    User user = User
            .create(userdata.getEmail(), userdata.getName(), userdata.getAvatar(), userdata.getPassword(), Role.getRoleByName(userdata.getRole()))
            .getOrElseThrow(failure -> new BadRequestDto(failure, Arrays.stream(failure.getProblems())
            .map(FieldInvalidDto::new)
            .toArray(FieldInvalidDto[]::new)));
    return userservice
            .createNewUser(user)
            .thenApply(either -> either.getOrElseThrow(InternalServerErrorDto::new))
            .thenApply(UserDto::new);


  }


  @GetMapping("/createRandom")
  public CompletableFuture<User> createRandom() {
    return userservice.createNewUser(User.createRandomFakeUser()
            .getOrElseThrow(failure -> new BadRequestDto(failure, Arrays.stream(failure.getProblems())
                    .map(FieldInvalidDto::new)
                    .toArray(FieldInvalidDto[]::new))))
            .thenApply(either -> either.getOrElseThrow(InternalServerErrorDto::new));


  }

  @GetMapping("/{id}")
  public CompletableFuture<User> getUser(@PathVariable int id) {
    return userservice.getUserById(id).thenApply(Either::get);}

  @DeleteMapping("/{id}")
  public CompletableFuture<Void> deleteUser(@PathVariable int id) {
    return userservice.deleteUserById(id).thenApply(either -> either.getOrElseThrow(InternalServerErrorDto::new));
  }


}
