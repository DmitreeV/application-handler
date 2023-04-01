package test.task.applicationhandler.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import test.task.applicationhandler.dto.ReqCreateDto;
import test.task.applicationhandler.dto.ReqDto;
import test.task.applicationhandler.dto.ReqUpdateDto;
import test.task.applicationhandler.model.Request;

@Mapper(componentModel = "spring")
public interface ReqMapper {

    @Mapping(target = "user.id", source = "user")
    Request toRequest(ReqCreateDto reqCreateDto);

    ReqDto toReqDto(Request request);

    Request toReq(ReqUpdateDto reqUpdateDto);
}
