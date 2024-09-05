package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.UserService;

@Controller
public class UserPage extends Page{

    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    @Guest
    public String currentUser(@PathVariable("id") String id, Model model) {
        try {
            model.addAttribute("selectUser", userService.findById(Long.parseLong(id)));
        } catch (NumberFormatException ignored) {
            ///Nothing
        }
        return "UserPage";
    }

    @GetMapping("/user/")
    @Guest
    public String emptyPage() {
        return "UserPage";
    }

}
