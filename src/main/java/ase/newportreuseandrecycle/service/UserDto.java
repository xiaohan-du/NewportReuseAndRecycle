package ase.newportreuseandrecycle.service;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
