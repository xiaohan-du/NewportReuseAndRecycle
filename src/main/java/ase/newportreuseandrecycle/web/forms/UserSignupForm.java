package ase.newportreuseandrecycle.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupForm {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
