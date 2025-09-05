package com.enomyfinances.controller;

import com.enomyfinances.model.User;
import com.enomyfinances.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth") // Base path for all authentication-related routes
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Renders register.jsp
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register"; // Return to registration form if validation fails
        }
        userService.registerUser(user);
        return "redirect:/Login"; // Redirect to login after success
    }
    
    
}
