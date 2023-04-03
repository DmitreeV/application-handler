package test.task.applicationhandler.service;

import test.task.applicationhandler.dto.ReqDto;

import java.util.List;

public interface ReqOperatorService {

    ReqDto processingRequestByOperator(Long reqId, Boolean approved);

    List<ReqDto> getAllSentRequestsWithSortFromNewToOld(Integer from, Integer size);

    List<ReqDto> getAllSentRequestsByUserNameWithSortFromNewToOld(String name, Integer from, Integer size);

    List<ReqDto> getAllSentRequestsByUserNameWithSortFromOldToNew(String name, Integer from, Integer size);

    List<ReqDto> getAllSentRequestsWithSortFromOldToNew(Integer from, Integer size);
}
