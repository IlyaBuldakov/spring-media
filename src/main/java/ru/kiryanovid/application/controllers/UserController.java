package ru.kiryanovid.application.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kiryanovid.application.dto.users.UserDto;
import ru.kiryanovid.application.dto.users.UserRequestDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "api/users")
@RequiredArgsConstructor
public class UserController {
    @GetMapping
    public List<UserDto> getAll(){
        return null;
    }
    @PostMapping
    public void create(@RequestBody UserRequestDto userRequestDto){

    }
    @GetMapping(path = "/{id}")
    public CompletableFuture<UserDto> getUserById(@PathVariable Integer id){
        return null;
    }
    @PutMapping(path ="/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody UserRequestDto userRequestDto){

    }
    @DeleteMapping(path = "/{id}")
    public void deleteUserById(@PathVariable Integer id){

    }


}
