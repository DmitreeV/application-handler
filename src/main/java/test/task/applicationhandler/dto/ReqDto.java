package test.task.applicationhandler.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import test.task.applicationhandler.model.ReqState;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqDto {

    private Long id;

    private String name;

    private String userMessage;

    private UserShortDto user;

    private ReqState state;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;
}
