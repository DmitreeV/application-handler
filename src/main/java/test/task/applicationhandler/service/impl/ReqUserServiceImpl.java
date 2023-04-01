package test.task.applicationhandler.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.task.applicationhandler.dto.ReqCreateDto;
import test.task.applicationhandler.dto.ReqDto;
import test.task.applicationhandler.dto.ReqUpdateDto;
import test.task.applicationhandler.exception.ConflictException;
import test.task.applicationhandler.exception.NotFoundException;
import test.task.applicationhandler.mapper.ReqMapper;
import test.task.applicationhandler.model.ReqState;
import test.task.applicationhandler.model.Request;
import test.task.applicationhandler.model.User;
import test.task.applicationhandler.repository.ReqRepository;
import test.task.applicationhandler.repository.UserRepository;
import test.task.applicationhandler.service.ReqUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static test.task.applicationhandler.model.ReqState.SENT;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReqUserServiceImpl implements ReqUserService {

    private final ReqRepository reqRepository;
    private final UserRepository userRepository;
    private final ReqMapper reqMapper;

    @Override
    @Transactional
    public ReqDto createRequest(Long userId, ReqCreateDto reqCreateDto) {
        User user = getUser(userId);
        Request request = reqMapper.toRequest(reqCreateDto);

        request.setUser(user);
        request.setCreatedOn(LocalDateTime.now());

        log.info("Saved new request with id : {}.", request.getId());
        return reqMapper.toReqDto(reqRepository.save(request));
    }

    @Override
    @Transactional
    public ReqDto updateRequestByUser(Long userId, Long reqId, ReqUpdateDto reqUpdateDto) {
        Request reqToUpdate = getRequest(reqId);
        Request request = reqMapper.toReq(reqUpdateDto);

        if (!reqToUpdate.getUser().getId().equals(userId)) {
            throw new ConflictException("Only the creator of the request can change it.");
        }
        if (reqToUpdate.getState() != (ReqState.DRAFT)) {
            throw new ConflictException("You can only change requests with the DRAFT status.");
        }

        reqToUpdate.setName(request.getName());
        reqToUpdate.setUserMessage(request.getUserMessage());

        log.info("Updated request with id {}.", reqId);
        return reqMapper.toReqDto(reqRepository.save(reqToUpdate));
    }

    @Override
    public List<ReqDto> getAllRequestsByUserIdWithSortFromNewToOld(Long userId, Integer from, Integer size) {
        getUser(userId);
        Page<Request> requests = reqRepository.findAllByUserIdOrderByCreatedOnAsc(userId, PageRequest.of(from / size, size));

        log.info("Received a list of all-user with id {} requests sorted from newer to older.", userId);
        return requests.stream()
                .map(reqMapper::toReqDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReqDto> getAllRequestsByUserIdWithSortFromOldToNew(Long userId, Integer from, Integer size) {
        getUser(userId);
        Page<Request> requests = reqRepository.findAllByUserIdOrderByCreatedOnDesc(userId, PageRequest.of(from / size, size));

        log.info("Received a list of all-user with id {} requests sorted from older to newer.", userId);
        return requests.stream()
                .map(reqMapper::toReqDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReqDto sentRequestForConsideration(Long reqId, Long userId) {
        Request request = reqRepository.findByIdAndUserId(reqId, userId)
                .orElseThrow(() -> new ConflictException("Only the creator of the request can submit them for consideration."));
        request.setState(SENT);
        log.info("The request has been sent to the consideration.");
        return reqMapper.toReqDto(reqRepository.save(request));
    }

    @Override
    @Transactional
    public void userDeleteRequest(Long reqId, Long userId) {
        getUser(userId);
        Request request = getRequest(reqId);

        if (!Objects.equals(request.getUser().getId(), userId)) {
            throw new ConflictException("Only the creator of the request can delete it.");
        }
        if (request.getState() != (ReqState.DRAFT)) {
            throw new ConflictException("You can only delete requests with the DRAFT status.");
        }

        reqRepository.deleteById(reqId);
        log.info("The request was deleted by the user with id {}.", userId);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User with id=%d not found", userId)));
    }

    private Request getRequest(Long reqId) {
        return reqRepository.findById(reqId).orElseThrow(() ->
                new NotFoundException(String.format("Request with id=%d not found", reqId)));
    }
}
