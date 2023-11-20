package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUsers();

    User getUser(Long id);

    void updateUser(User user, Set<Role> roles);

    void deleteUser(Long id);

    User getUserName(String name);

    void saveNewUser(User user, Set<Role> roles);
}
