package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.User;

import java.util.List;

public interface UserRepository {
    List<User> getUsers();
    void addNewUser(User aUser);
}