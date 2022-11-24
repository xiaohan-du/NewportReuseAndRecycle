package ase.newportreuseandrecycle.domain;
import lombok.Data;

@Data
public class User {

    private Integer id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    public User(Integer id, String email, String password, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}