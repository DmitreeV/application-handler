package test.task.applicationhandler.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import test.task.applicationhandler.dto.ReqDto;
import test.task.applicationhandler.service.ReqOperatorService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/operator/requests")
public class ReqOperatorController {

    private final ReqOperatorService reqOperatorService;

    @PatchMapping("/{reqId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ReqDto processingRequestByOperator(@PathVariable Long reqId, @RequestParam Boolean approved) {
        return reqOperatorService.processingRequestByOperator(reqId, approved);
    }

    @GetMapping("/search/new")
    @ResponseStatus(value = HttpStatus.OK)
    List<ReqDto> getAllSentRequestsByUserNameWithSortFromOldToNew(@RequestParam String name,
                                                                  @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                  @Positive @RequestParam(defaultValue = "5") Integer size) {
        return reqOperatorService.getAllSentRequestsByUserNameWithSortFromOldToNew(name, from, size);
    }

    @GetMapping("/search/old")
    @ResponseStatus(value = HttpStatus.OK)
    List<ReqDto> getAllSentRequestsByUserNameWithSortFromNewToOld(@RequestParam String name,
                                                                  @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                  @Positive @RequestParam(defaultValue = "5") Integer size) {
        return reqOperatorService.getAllSentRequestsByUserNameWithSortFromNewToOld(name, from, size);
    }

    @GetMapping("/all/new")
    @ResponseStatus(value = HttpStatus.OK)
    List<ReqDto> getAllSentRequestsWithSortFromOldToNew(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                        @Positive @RequestParam(defaultValue = "5") Integer size) {
        return reqOperatorService.getAllSentRequestsWithSortFromOldToNew(from, size);
    }

    @GetMapping("/all/old")
    @ResponseStatus(value = HttpStatus.OK)
    List<ReqDto> getAllSentRequestsWithSortFromNewToOld(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                        @Positive @RequestParam(defaultValue = "5") Integer size) {
        return reqOperatorService.getAllSentRequestsWithSortFromNewToOld(from, size);
    }
}
