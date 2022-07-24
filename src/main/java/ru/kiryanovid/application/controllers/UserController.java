package ru.kiryanovid.application.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kiryanovid.application.dto.errors.InternalServerErrorDto;
import ru.kiryanovid.application.dto.users.UserDto;
import ru.kiryanovid.application.dto.users.UserRequestDto;
import ru.kiryanovid.domain.entity.errors.AlreadyExists;
import ru.kiryanovid.domain.entity.users.User;
import ru.kiryanovid.domain.usecases.user.CreateUser;
import ru.kiryanovid.domain.usecases.user.DeleteUserById;
import ru.kiryanovid.domain.usecases.user.GetAllUser;
import ru.kiryanovid.domain.usecases.user.GetUserByEmail;
import ru.kiryanovid.domain.usecases.user.GetUserById;
import ru.kiryanovid.domain.usecases.user.UpdateUser;


/**
 * Контроллер для работы с пользователями.
 */
@RestController
@RequestMapping(path = "api/users")
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Пользователи"))
@RequiredArgsConstructor
public class UserController {
  private final GetAllUser getAllUser;
  private final CreateUser createUser;
  private final GetUserById getUserById;

  private final GetUserByEmail getUserByEmail;
  private final UpdateUser updateUser;
  private final DeleteUserById deleteUserById;

  /**
   * Получение списка всех пользователей.
   *
   * @return Список всех пользователей
   *
   * @throws ExecutionException Ошибка выполнения
   * @throws InterruptedException Ошибка прерывания
   */
  @GetMapping
  @Async
  @Operation(summary = "Получение списка всех пользователей")
  public List<UserDto> getAll() throws ExecutionException, InterruptedException {
    var userList = getAllUser.execute(null)
                .get().get();
    var dtoList = new ArrayList<UserDto>();
    for (User user : userList) {
      dtoList.add(new UserDto(user));
    }
    return dtoList;
  }

  /**
  * Создание нового пользователя.
  *
  * @param userRequestDto Представление сущности пользователя
  *
  * @throws ExecutionException Ошибка выполнения
  * @throws InterruptedException Ошибка прерывания
  */
  @PostMapping
  @Async
  @Operation(summary = "Создание пользователя")
  public void create(@RequestBody UserRequestDto userRequestDto)
          throws ExecutionException, InterruptedException {
    var existingEmail = getUserByEmail.execute(userRequestDto.getEmail()).get().get();
    if (existingEmail > 0) {
      throw new InternalServerErrorDto(AlreadyExists.USER_WITH_EMAIL);
    }
    var user = User.create(0,
                userRequestDto.getName(),
                userRequestDto.getEmail(),
                userRequestDto.getPassword(),
                userRequestDto.getAvatar(),
                userRequestDto.getRole()
                );
    createUser.execute(user.get());
  }

  @GetMapping(path = "/{id}")
  @Async
  @Operation(summary = "Получение задачи по идентификатору")
  public CompletableFuture<UserDto> getUserById(@PathVariable Integer id)
          throws ExecutionException, InterruptedException {
    var user = getUserById.execute(id).get().get();
    return CompletableFuture.completedFuture(new UserDto(user));
  }

  /**
  * Обновляет пользователя.
  *
  * @param id Идентификатор пользователя
  *
  * @param userRequestDto Представление сущности пользователя
  */
  @PutMapping(path = "/{id}")
  @Async
  @Operation(summary = "Обновление пользователя по идентификатору")
  public void updateUser(@PathVariable Integer id, @RequestBody UserRequestDto userRequestDto) {
    var updatedUser = User.create(id,
                userRequestDto.getName(),
                userRequestDto.getEmail(),
                userRequestDto.getPassword(),
                userRequestDto.getAvatar(),
                userRequestDto.getRole()
                ).get();
    updateUser.execute(updatedUser);
  }

  @DeleteMapping(path = "/{id}")
  @Async
  @Operation(summary = "Удаление пользователя по идентификатору")
  public void deleteUserById(@PathVariable Integer id) {
    deleteUserById.execute(id);
  }
}
