package ru.job4j.forum.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.forum.model.Role;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final UserService users;

    public UserController(UserService users) {
        this.users = users;
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable long id) {
        users.delete(id);
    }

    @GetMapping("/users")
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users.getAll());
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @GetMapping("/users/update")
    public ModelAndView updateUser(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", users.findById(id));
        modelAndView.setViewName("user-edit");
        return modelAndView;
    }

    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute("user") User user) {
        this.users.update(user.getId(), user);
        return "redirect:/users";
    }

    @ModelAttribute("allRoles")
    public List<Role> getAllRoles() {
        List<Role> allRoles = new ArrayList<>();
        allRoles.add(new Role("ROLE_USER"));
        allRoles.add(new Role("ROLE_ADMIN"));
        return allRoles;
    }
}
