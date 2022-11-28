package ase.newportreuseandrecycle.domain;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private Integer id;

    private String username;

    private String password;

    private String role;

    private Boolean enabled;

}