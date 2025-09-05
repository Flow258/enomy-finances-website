package com.enomyfinances.controller;

import com.enomyfinances.dao.UserDAO;
import com.enomyfinances.model.User;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, 
                            @RequestParam String passwordhash, 
                            Model model, 
                            HttpSession session) {
        
        User user = userDAO.loginUser(email, passwordhash);
        
        if (user != null) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/profile"; // Redirect to profile page after login
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login_register"; // Return to login page with error
        }
    }
}
