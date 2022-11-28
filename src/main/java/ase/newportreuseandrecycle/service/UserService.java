package ase.newportreuseandrecycle.service;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    void addNewUser(UserDto userDto);
}
