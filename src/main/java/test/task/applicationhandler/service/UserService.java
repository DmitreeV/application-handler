package test.task.applicationhandler.service;

import test.task.applicationhandler.dto.UserDto;

public interface UserService { //created only for postman tests

    UserDto saveUser(UserDto userDto);
}
