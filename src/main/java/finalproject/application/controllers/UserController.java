package finalproject.application.controllers;

import finalproject.application.dto.failures.BadRequestDto;
import finalproject.application.dto.failures.NotFoundDto;
import finalproject.application.dto.user.UserDto;
import finalproject.application.dto.user.UserRequestDto;
import finalproject.application.services.AuthService;
import finalproject.application.services.UserService;
import finalproject.domain.entities.user.Role;
import finalproject.domain.entities.user.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для операций с Пользователями.
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

  UserService userService;
  AuthService authService;


  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping("/hello")
  public String helloAdmin() throws ExecutionException, InterruptedException {
    return userService.getUserById(authService.getId())
            .thenApply(either -> either.get()).get().getName();
  }

  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  @GetMapping
  public CompletableFuture<List<UserDto>> getUsers() {
    return userService.getAllUsers()
            .thenApply(either -> either.get().stream().map(UserDto::new).toList());
  }

  /**Оработка запроса на создание пользователя..
   *
   * @param userdata представление запроса создания пользователя.
   *
   */
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping
  public CompletableFuture<UserDto> createUser(
          @RequestBody UserRequestDto userdata) {
    User user = User
            .create(userdata.getEmail(), userdata.getName(),
                    userdata.getAvatar(), userdata.getPassword(),
                    Role.getRoleByName(userdata.getRole()))
            .getOrElseThrow(failure -> new BadRequestDto(failure));
    return userService
            .createNewUser(user)
            .thenApply(either -> either.getOrElseThrow(failure -> new BadRequestDto(failure)))
            .thenApply(UserDto::new);
  }

  /**Обработка запроса на изменение данных пользователя.
   *
   * @param id - id пользователя
   * @param userdata - представление запроса пользователя
   */
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/{id}")
  public CompletableFuture<UserDto> editUser(@PathVariable int id,
                                             @RequestBody UserRequestDto userdata) {

    User updatedUser = User
            .create(userdata.getEmail(), userdata.getName(),
                    userdata.getAvatar(), userdata.getPassword(),
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

  /**Получение пользователя.
   *
   * @param id - id пользователя
   * @return userDro представление пользователя
   */
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
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

  /**Обработка запроса на удаление пользователя.
   *
   * @param id - id пользователя
   */
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
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







}
