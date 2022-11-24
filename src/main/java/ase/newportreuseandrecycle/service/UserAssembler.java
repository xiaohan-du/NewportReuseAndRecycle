package ase.newportreuseandrecycle.service;

import ase.newportreuseandrecycle.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserAssembler {
    public static List<UserDto> toDto(List<User> users) {
        return users.stream().map(u -> toDto(u)).collect(Collectors.toList());
    }

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}
