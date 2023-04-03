package test.task.applicationhandler.mapper;

import org.mapstruct.Mapper;
import test.task.applicationhandler.dto.UserDto;
import test.task.applicationhandler.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser(UserDto userDto); //created only for postman tests
}
