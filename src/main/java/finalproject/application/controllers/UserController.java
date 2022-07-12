package finalproject.application.controllers;

import finalproject.application.dto.failures.BadRequestDto;
import finalproject.application.dto.failures.NotFoundDto;
import finalproject.application.dto.user.UserDto;
import finalproject.application.dto.user.UserRequestDto;
import finalproject.application.services.UserService;
import finalproject.domain.entities.user.Role;
import finalproject.domain.entities.user.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

  UserService userService;


  @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping("/hello")
  public String helloAdmin() throws ExecutionException, InterruptedException {
    return getCurrentUser().getName();
  }
  @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping
  public CompletableFuture<List<UserDto>> getUsers() {
    return userService.getAllUsers().thenApply(either -> either.get().stream().map(UserDto::new).toList());
  }

  @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping
  public CompletableFuture<UserDto> createUser(@RequestBody UserRequestDto userdata) {
    User user = User
            .create(userdata.getEmail(), userdata.getName(), userdata.getAvatar(), userdata.getPassword(),
                    Role.getRoleByName(userdata.getRole()))
            .getOrElseThrow(failure -> new BadRequestDto(failure));
    return userService
            .createNewUser(user)
            .thenApply(either -> either.getOrElseThrow(failure -> new BadRequestDto(failure)))
            .thenApply(UserDto::new);
  }

  @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/{id}")
  public CompletableFuture<UserDto> editUser(@PathVariable int id, @RequestBody UserRequestDto userdata) {

    User updatedUser = User
            .create(userdata.getEmail(), userdata.getName(), userdata.getAvatar(), userdata.getPassword(),
                    Role.getRoleByName(userdata.getRole()))
            .getOrElseThrow(failure -> new BadRequestDto(failure));
    updatedUser.setId(id);
    return userService.editUser(updatedUser, id)
            .thenApply(either -> either.getOrElseThrow(failure -> {
              if (failure.getProblems() != null) {
                return new BadRequestDto(failure);
              }
              return new NotFoundDto(failure);
            })).thenApply(UserDto::new);
  }

  @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping("/{id}")
  public CompletableFuture<UserDto> getUser(@PathVariable int id) {
    return userService.getUserById(id)
      .thenApply(either -> either.getOrElseThrow(failure -> {
        if (failure.getProblems() != null) {
          return new BadRequestDto(failure);
        }
        return new NotFoundDto(failure);
      }))
      .thenApply(UserDto::new);
  }

  @ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })
  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{id}")
  public CompletableFuture<Void> deleteUser(@PathVariable int id) {
    return userService.deleteUserById(id).thenApply(either -> either.getOrElseThrow(failure -> {
      if (failure.getProblems() != null) {
        return new BadRequestDto(failure);
      }
      return new NotFoundDto(failure);
    }));
  }

  private User getCurrentUser() throws ExecutionException, InterruptedException {
    return userService.getUserById((Integer) SecurityContextHolder.getContext().getAuthentication().getDetails()).get().get();
  }





}
