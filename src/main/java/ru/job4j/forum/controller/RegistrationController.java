package ru.job4j.forum.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@Controller
public class RegistrationController {
    private final PasswordEncoder encoder;
    private final UserService userService;

    public RegistrationController(PasswordEncoder encoder,
                                  UserService userService) {
        this.encoder = encoder;
        this.userService = userService;
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }

    @PostMapping("/reg")
    public String reg(@ModelAttribute User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);
        userService.save(user);
        return "login";
    }
}
