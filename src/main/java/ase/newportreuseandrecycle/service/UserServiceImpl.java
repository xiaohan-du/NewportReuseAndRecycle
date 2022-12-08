package ase.newportreuseandrecycle.service;

import ase.newportreuseandrecycle.data.UserRepository;
import ase.newportreuseandrecycle.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
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
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRole(),
                userDto.getEnabled()
        );
        userRepository.addNewUser(newUser);
    }

    @Override
    public Optional<UserDto> getUserByUsername(String username) {
        Optional<User> aUser = userRepository.getAUserByUsername(username);
        if (aUser.isPresent()) {
            return Optional.of(UserAssembler.toDto(aUser.get()));
        } else {
            return Optional.empty();
        }

    }

}




