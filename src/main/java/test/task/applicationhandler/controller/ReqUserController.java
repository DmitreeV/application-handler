package test.task.applicationhandler.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import test.task.applicationhandler.dto.ReqCreateDto;
import test.task.applicationhandler.dto.ReqDto;
import test.task.applicationhandler.dto.ReqUpdateDto;
import test.task.applicationhandler.service.ReqUserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users/{userId}/requests")
public class ReqUserController {

    private final ReqUserService reqUserService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ReqDto createRequest(@PathVariable Long userId, @Valid @RequestBody ReqCreateDto reqCreateDto) {
        return reqUserService.createRequest(userId, reqCreateDto);
    }

    @PatchMapping("/{reqId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ReqDto updateRequestByUser(@PathVariable Long userId, @PathVariable Long reqId,
                                      @Valid @RequestBody ReqUpdateDto reqUpdateDto) {
        return reqUserService.updateRequestByUser(userId, reqId, reqUpdateDto);
    }

    @PatchMapping("/{reqId}/sent")
    @ResponseStatus(value = HttpStatus.OK)
    public ReqDto sentRequestForConsideration(@PathVariable Long reqId, @PathVariable Long userId) {
        return reqUserService.sentRequestForConsideration(reqId, userId);
    }

    @GetMapping("/new")
    @ResponseStatus(value = HttpStatus.OK)
    List<ReqDto> getAllRequestsByUserIdWithSortFromOldToNew(@PathVariable Long userId,
                                                            @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                            @Positive @RequestParam(defaultValue = "5") Integer size) {
        return reqUserService.getAllRequestsByUserIdWithSortFromOldToNew(userId, from, size);
    }

    @GetMapping("/old")
    @ResponseStatus(value = HttpStatus.OK)
    List<ReqDto> getAllRequestsByUserIdWithSortFromNewToOld(@PathVariable Long userId,
                                                            @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                            @Positive @RequestParam(defaultValue = "5") Integer size) {
        return reqUserService.getAllRequestsByUserIdWithSortFromNewToOld(userId, from, size);
    }

    @DeleteMapping("/{reqId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void userDeleteRequest(@PathVariable Long reqId, @PathVariable Long userId) {
        reqUserService.userDeleteRequest(reqId, userId);
    }
}
