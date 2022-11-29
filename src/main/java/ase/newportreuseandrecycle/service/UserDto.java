package ase.newportreuseandrecycle.service;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private String role;
    private Boolean enabled;
}
