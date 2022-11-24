package ase.newportreuseandrecycle.service;

import ase.newportreuseandrecycle.data.UserRepository;
import ase.newportreuseandrecycle.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository repo) {
        this.userRepository = repo;
    }
    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.getUsers();
        return UserAssembler.toDto(users);
    }

    @Override
    public void addNewUser(UserDto userDto) {
        User newUser = new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getFirstName(),
                userDto.getLastName()
        );
        userRepository.addNewUser(newUser);
    }

}
