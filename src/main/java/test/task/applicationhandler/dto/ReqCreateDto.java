package test.task.applicationhandler.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqCreateDto {

    @NotBlank(message = "'name' can not be blank")
    private String name;

    @Size(min = 10, max = 1000)
    @NotBlank(message = "'user_message' can not be blank")
    private String userMessage;

    @Positive
    private Long user;
}
