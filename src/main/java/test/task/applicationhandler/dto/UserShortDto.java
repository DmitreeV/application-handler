package test.task.applicationhandler.dto;

import lombok.*;
import test.task.applicationhandler.model.UserRole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserShortDto {

    private Long id;

    @NotBlank(message = "'name' can not be blank")
    private String name;

    @NotNull
    private UserRole role;
}
