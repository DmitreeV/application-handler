package test.task.applicationhandler.service;

import test.task.applicationhandler.dto.ReqCreateDto;
import test.task.applicationhandler.dto.ReqDto;
import test.task.applicationhandler.dto.ReqUpdateDto;

import java.util.List;

public interface ReqUserService {

    ReqDto createRequest(Long userId, ReqCreateDto reqCreateDto);

    ReqDto updateRequestByUser(Long userId, Long reqId, ReqUpdateDto reqUpdateDto);

    List<ReqDto> getAllRequestsByUserIdWithSortFromNewToOld(Long userId, Integer from, Integer size);

    List<ReqDto> getAllRequestsByUserIdWithSortFromOldToNew(Long userId, Integer from, Integer size);

    ReqDto sentRequestForConsideration(Long reqId, Long userId);

    void userDeleteRequest(Long reqId, Long userId);
}

