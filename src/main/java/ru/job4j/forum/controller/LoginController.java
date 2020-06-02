package ru.job4j.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class LoginController {
    @Autowired
    MessageSource messageSource;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Locale locale,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = messageSource.getMessage("msg.incorrect_credentials", null, LocaleContextHolder.getLocale());
        } else if (logout != null) {
            errorMessage = messageSource.getMessage("msg.successful_logout", null, LocaleContextHolder.getLocale());
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/login?logout=true";
    }
}
