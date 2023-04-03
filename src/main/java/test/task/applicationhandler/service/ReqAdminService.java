package test.task.applicationhandler.service;

import test.task.applicationhandler.dto.UserDto;

import java.util.List;

public interface ReqAdminService {

    List<UserDto> getAllUsers(Integer from, Integer size);

    UserDto searchUserByName(String name);

    UserDto assignOperator(Long userId);
}
