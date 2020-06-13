package ru.job4j.forum.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.Role;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository users;
    private final PasswordEncoder encoder;

    public UserService(UserRepository users, PasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    public User save(User user) {
        user.addAuthority(new Authority(user, Role.ROLE_USER));
        user.setPassword(encoder.encode(user.getPassword()));
        return users.save(user);
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        users.findAll().forEach(result::add);
        return result;
    }

    public void delete(String userName) {
        User delUser = users.findUserByUsername(userName);
        users.delete(delUser);
    }

    public User update(User user) {
        User storedUser = users.findUserByUsername(user.getUsername());
        if (!storedUser.getPassword().equals(user.getPassword())) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        return this.save(user);
    }

    public User getUserByUsername(String username) {
        return users.findUserByUsername(username);
    }

}
