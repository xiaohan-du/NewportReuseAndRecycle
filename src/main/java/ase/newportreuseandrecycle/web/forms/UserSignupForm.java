package ase.newportreuseandrecycle.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupForm {
    private Integer id;
    @NotEmpty(message = "{username.invalid}")
    private String username;
    private String password;
    private String role;
}
