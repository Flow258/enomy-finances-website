package com.enomyfinances.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.enomyfinances.model.*;

@Controller
public class SignUpController {

    
    //@Autowired
    //private UserRepository userRepository;

    // Display the registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "login_register"; // JSP file
    }


}
