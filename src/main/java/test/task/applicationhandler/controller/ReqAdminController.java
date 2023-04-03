package test.task.applicationhandler.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import test.task.applicationhandler.dto.UserDto;
import test.task.applicationhandler.service.ReqAdminService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/users")
public class ReqAdminController {

    private final ReqAdminService reqAdminService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserDto> getAllUsers(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                     @Positive @RequestParam(defaultValue = "10") Integer size) {
        return reqAdminService.getAllUsers(from, size);
    }

    @GetMapping("/search")
    @ResponseStatus(value = HttpStatus.OK)
    UserDto searchUserByName(@RequestParam String name) {
        return reqAdminService.searchUserByName(name);
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserDto assignOperator(@PathVariable Long userId) {
        return reqAdminService.assignOperator(userId);
    }
}
