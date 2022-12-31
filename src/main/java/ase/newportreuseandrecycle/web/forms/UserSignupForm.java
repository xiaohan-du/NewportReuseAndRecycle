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
    @NotEmpty(message = "{password.invalid}")
    private String password;
    private String role;
}
