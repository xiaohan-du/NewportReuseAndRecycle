package ase.newportreuseandrecycle.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupForm {
    private Integer id;
    private String username;
    private String password;
    private String role;
}
