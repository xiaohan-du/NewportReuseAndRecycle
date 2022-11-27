package ase.newportreuseandrecycle.domain;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private Integer id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

}