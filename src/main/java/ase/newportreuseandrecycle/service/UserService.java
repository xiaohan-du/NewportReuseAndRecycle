package ase.newportreuseandrecycle.service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getUsers();
    void addNewUser(UserDto userDto);
    Optional<UserDto> getUserById(Integer id);
    Optional<UserDto> getUserByUsername(String username);
}
