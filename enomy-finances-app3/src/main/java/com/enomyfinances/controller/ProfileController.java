package com.enomyfinances.controller;

import com.enomyfinances.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String showProfilePage(HttpSession session, Model model) {
        // Get the logged-in user from session
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login"; // Redirect to login if user is not logged in
        }

        // Add user to the model so it can be displayed on the page
        model.addAttribute("user", user);
        return "profile"; // Return profile.jsp (or profile.html)
    }
}
