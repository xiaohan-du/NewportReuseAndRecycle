package ase.newportreuseandrecycle.service;

import ase.newportreuseandrecycle.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getUsers();
    void addNewUser(UserDto userDto);
    Optional<UserDto> getAUserByEmail(String email);

    Boolean checkUserPasswordMatch(String email, String password);
}
