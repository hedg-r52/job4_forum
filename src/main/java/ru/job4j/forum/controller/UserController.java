package ru.job4j.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@Controller
public class UserController {
    private final UserService users;

    public UserController(UserService users) {
        this.users = users;
    }

    @PostMapping(value = "/user/delete")
    public String deleteUser(@ModelAttribute User user) {
        long id = user.getId();
        users.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.addObject("users", users.getAll());
        modelAndView.setViewName("users-edit");
        return modelAndView;
    }

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute User user) {
        users.update(user.getId(), user);
        return "redirect:/users";
    }
}
