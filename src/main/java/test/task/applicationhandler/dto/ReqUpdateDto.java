package test.task.applicationhandler.dto;

import lombok.*;
import test.task.applicationhandler.model.ReqState;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqUpdateDto {

    private Long id;

    @NotBlank(message = "'name' can not be blank")
    private String name;

    @Size(min = 10, max = 1000)
    @NotBlank(message = "'user_message' can not be blank")
    private String userMessage;

    @NotNull
    private ReqState state;
}
