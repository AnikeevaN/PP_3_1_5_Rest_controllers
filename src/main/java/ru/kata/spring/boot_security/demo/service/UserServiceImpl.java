package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDao userDao;

    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserDao userDao) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public void updateUser(User user, Set<Role> roles) {
        String password = getUser(user.getId()).getPassword();
        if (user.getPassword().equals(password)) {
            user.setPassword(password);
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        user.setRoles(roles);
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserName(String name) {
        return userDao.getUserName(name);
    }

    @Override
    @Transactional
    public void saveNewUser(User user, Set<Role> roles) {
        user.setRoles(roles);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }
}
