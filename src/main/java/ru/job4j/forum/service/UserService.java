package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Role;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.RoleRepository;
import ru.job4j.forum.store.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static final String ROLE_USER = "ROLE_USER";

    private final UserRepository users;
    private final RoleRepository roles;

    public UserService(UserRepository users, RoleRepository roles) {
        this.users = users;
        this.roles = roles;
    }

    public User save(User user) {
        User savedUser = users.save(user);
        Role role = new Role(ROLE_USER);
        role.setUser(user);
        roles.save(role);
        return savedUser;
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        users.findAll().forEach(result::add);
        return result;
    }

    public void delete(long id) {
        users.deleteById(id);
    }

    public User update(long id, User user) {
        user.setId(id);
        return this.save(user);
    }

}
