package test.task.applicationhandler.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import test.task.applicationhandler.dto.UserDto;
import test.task.applicationhandler.service.UserService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/users")
public class UserController { //created only for postman tests

    private final UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto saveUser(@Valid @RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }
}