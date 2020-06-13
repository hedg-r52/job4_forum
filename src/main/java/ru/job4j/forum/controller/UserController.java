package ru.job4j.forum.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.forum.model.Role;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

import java.util.ArrayList;

@Controller
public class UserController {
    private final UserService users;

    public UserController(UserService users) {
        this.users = users;
    }

    @DeleteMapping("/users/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String userName) {
        users.delete(userName);
    }

    @GetMapping("/users")
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users.getAll());
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @GetMapping("/users/update")
    public ModelAndView updateUser(@RequestParam String username) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", users.getUserByUsername(username));
        modelAndView.addObject("allRoles", Role.values());
        modelAndView.addObject("selectedRoles", new ArrayList<Role>());
        modelAndView.setViewName("user-edit");
        return modelAndView;
    }

    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute("user") User user) {
        this.users.update(user);
        return "redirect:/users";
    }

}
