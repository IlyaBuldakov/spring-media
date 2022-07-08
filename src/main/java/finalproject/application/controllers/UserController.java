package finalproject.application.controllers;

import finalproject.application.dto.failures.BadRequestDto;
import finalproject.application.dto.failures.NotFoundDto;
import finalproject.application.dto.user.UserDto;
import finalproject.application.dto.user.UserRequestDto;
import finalproject.application.services.UserService;
import finalproject.domain.entities.user.Role;
import finalproject.domain.entities.user.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

  UserService userservice;


  @GetMapping
  public CompletableFuture<List<UserDto>> getUsers() {
    return userservice.getAllUsers().thenApply(either -> either.get().stream().map(UserDto::new).toList());
  }

  @PutMapping
  public CompletableFuture<UserDto> createUser(@RequestBody UserRequestDto userdata) {
    User user = User
            .create(userdata.getEmail(), userdata.getName(), userdata.getAvatar(), userdata.getPassword(),
                    Role.getRoleByName(userdata.getRole()))
            .getOrElseThrow(failure -> new BadRequestDto(failure));
    return userservice
            .createNewUser(user)
            .thenApply(either -> either.getOrElseThrow(failure -> new BadRequestDto(failure)))
            .thenApply(UserDto::new);
  }

  @PutMapping("/{id}")
  public CompletableFuture<UserDto> editUser(@PathVariable int id, @RequestBody UserRequestDto userdata) {

    User updatedUser = User
            .create(userdata.getEmail(), userdata.getName(), userdata.getAvatar(), userdata.getPassword(),
                    Role.getRoleByName(userdata.getRole()))
            .getOrElseThrow(failure -> new BadRequestDto(failure));
    updatedUser.setId(id);
    return userservice.editUser(updatedUser, id)
            .thenApply(either -> either.getOrElseThrow(failure -> {
              if (failure.getProblems() != null) {
                return new BadRequestDto(failure);
              }
              return new NotFoundDto(failure);
            })).thenApply(UserDto::new);
  }

  @GetMapping("/{id}")
  public CompletableFuture<UserDto> getUser(@PathVariable int id) {
    return userservice.getUserById(id)
      .thenApply(either -> either.getOrElseThrow(failure -> {
        if (failure.getProblems() != null) {
          return new BadRequestDto(failure);
        }
        return new NotFoundDto(failure);
      }))
      .thenApply(UserDto::new);
  }

  @DeleteMapping("/{id}")
  public CompletableFuture<Void> deleteUser(@PathVariable int id) {
    return userservice.deleteUserById(id).thenApply(either -> either.getOrElseThrow(failure -> {
      if (failure.getProblems() != null) {
        return new BadRequestDto(failure);
      }
      return new NotFoundDto(failure);
    }));
  }







}
