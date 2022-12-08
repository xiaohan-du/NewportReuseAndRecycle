package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getUsers();

    void addNewUser(User aUser);

    Optional<User> getAUserByUsername(String username);
}


