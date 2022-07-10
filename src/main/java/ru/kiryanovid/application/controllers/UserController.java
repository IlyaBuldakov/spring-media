package ru.kiryanovid.application.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kiryanovid.application.dto.users.UserDto;
import ru.kiryanovid.application.dto.users.UserRequestDto;
import ru.kiryanovid.domain.entity.users.User;
import ru.kiryanovid.domain.usecases.user.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "api/users")
@RequiredArgsConstructor
public class UserController {
    private final GetAllUser getAllUser;
    private final CreateUser createUser;
    private final GetUserById getUserById;
    private final UpdateUser updateUser;
    private final DeleteUserById deleteUserById;

    @GetMapping
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
    public void create(@RequestBody UserRequestDto userRequestDto){
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
    public CompletableFuture<UserDto> getUserById(@PathVariable Integer id) throws ExecutionException, InterruptedException {
        var user = getUserById.execute(id).get().get();
        return CompletableFuture.completedFuture(new UserDto(user));
    }
    @PutMapping(path ="/{id}")
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
    public void deleteUserById(@PathVariable Integer id){
        deleteUserById.execute(id);
    }
}
