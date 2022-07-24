package ru.kiryanovid.application.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import ru.kiryanovid.application.dto.errors.InternalServerErrorDto;
import ru.kiryanovid.application.dto.users.UserDto;
import ru.kiryanovid.application.dto.users.UserRequestDto;
import ru.kiryanovid.domain.entity.errors.AlreadyExists;
import ru.kiryanovid.domain.entity.users.User;
import ru.kiryanovid.domain.usecases.user.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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


    @GetMapping
    @Async
    @Operation(summary = "Получение списка всех пользователей")
    public List<UserDto> getAll() throws ExecutionException, InterruptedException {
        var userList = getAllUser.execute(null)
                .get().get();
        var dtoList = new ArrayList<UserDto>();
        for (User user: userList){
            dtoList.add(new UserDto(user));
        }
        return dtoList;
    }
    @PostMapping
    @Async
    @Operation(summary = "Создание пользователя")
    public void create(@RequestBody UserRequestDto userRequestDto) throws ExecutionException, InterruptedException {
        var existingEmail = getUserByEmail.execute(userRequestDto.getEmail()).get().get();
        if(existingEmail > 0){
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
    public CompletableFuture<UserDto> getUserById(@PathVariable Integer id) throws ExecutionException, InterruptedException {
        var user = getUserById.execute(id).get().get();
        return CompletableFuture.completedFuture(new UserDto(user));
    }
    @PutMapping(path ="/{id}")
    @Async
    @Operation(summary = "Обновление пользователя по идентификатору")
    public void updateUser(@PathVariable Integer id, @RequestBody UserRequestDto userRequestDto){
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
    public void deleteUserById(@PathVariable Integer id){
        deleteUserById.execute(id);
    }
}
