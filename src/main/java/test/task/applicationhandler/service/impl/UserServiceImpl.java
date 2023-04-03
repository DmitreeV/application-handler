package test.task.applicationhandler.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.task.applicationhandler.dto.UserDto;
import test.task.applicationhandler.error.exception.ConflictException;
import test.task.applicationhandler.mapper.UserMapper;
import test.task.applicationhandler.model.User;
import test.task.applicationhandler.repository.UserRepository;
import test.task.applicationhandler.service.UserService;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService { //created only for postman tests

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("User is already exists.");
        }
        log.info("Saved new user {}.", userDto);
        return userMapper.toUserDto(user);
    }
}