package com.enomyfinances.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.enomyfinances.dao.UserDAO;
import com.enomyfinances.model.User;

@Controller
public class HelloController {
	
	@Autowired
    private UserDAO userDAO;
	
    @GetMapping("/")
    public ModelAndView showHome() {
    	System.out.print("Hello");
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("message", "Hello, Spring MVC without Spring Boot!");
        return mav;
    }
    
    @GetMapping("/Dashboard")
    public ModelAndView showUserDashboard() {
        ModelAndView mav = new ModelAndView("userdashboard");
        mav.addObject("message", "Hi");
        return mav;
    }
    
    @GetMapping("/Login")
    public ModelAndView showLogin_Register() {
        ModelAndView mav = new ModelAndView("login_register");
        mav.addObject("message", "Eyyy");
        System.out.println("Login");
        return mav;
    }
    @GetMapping("/Register")
    public ModelAndView showRegister() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("message", "Eyyy");
        return mav;
    }
    
    @GetMapping("/LoginHome")
    public ModelAndView showLoginHome() {
        ModelAndView mav = new ModelAndView("LoginHome");
        mav.addObject("message", "Eyyy");
        return mav;
    }
   
    @GetMapping("/Profile")
    public ModelAndView showProfile(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return new ModelAndView("redirect:/login_register"); // Redirect to login if user is not logged in
        }

        ModelAndView mav = new ModelAndView("profile");
        mav.addObject("user", user); // Pass user details to the profile page
        return mav;
    }
    
    @GetMapping("/CurrencyExchange")
    public ModelAndView showCurrencyExchange() {
        ModelAndView mav = new ModelAndView("currency_exchange");
        mav.addObject("message", "Currency Exchange Page");
        return mav;
    }
    
    

    
    
    /*
    @GetMapping("/profile")
    public String profile(HttpSession session) {
        session.invalidate();
        return "redirect:/Profile";
    }
    
   */
    
    
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear session
        return "redirect:/Login"; // Redirect to login page
    }

    
    // Process the registration form
    @PostMapping("/signup") // 🔥 Change this to avoid conflict
    public String registerUser(@ModelAttribute("user") User user, BindingResult result, Model model) {
        System.out.println("form inputed");
    	if (result.hasErrors()) {
            return "register"; // Stay on the form if errors
        }

        // Check if email already exists
        /*
        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already registered");
            return "register";
        }
*/
        //userRepository.saveUser(user); // Save the user
        System.out.println(user);
        userDAO.registerUser(user);
        return "redirect:/Login"; // Redirect to dashboard on success
    }
    
    @PostMapping(value = "/convertCurrency", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> convertCurrency(
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency,
            @RequestParam double amount) {

        Map<String, Object> response = new HashMap<>();

        try {
            double feePercentage = getFeePercentage(amount);
            double feeAmount = amount * feePercentage;
            double netAmount = amount - feeAmount;
            double exchangeRate = 0.92; // Simulated exchange rate
            double convertedAmount = netAmount * exchangeRate;

            response.put("originalAmount", amount);
            response.put("feePercentage", feePercentage);
            response.put("feeAmount", feeAmount);
            response.put("netAmount", netAmount);
            response.put("exchangeRate", exchangeRate);
            response.put("convertedAmount", convertedAmount);
            response.put("toCurrency", toCurrency);
        } catch (Exception e) {
            response.put("error", "An error occurred during conversion.");
        }

        return response;
    }


    // Helper method for fee calculation
    private double getFeePercentage(double amount) {
        if (amount > 2500) {
            return 0.015; // 1.5%
        } else if (amount > 1500) {
            return 0.02; // 2.0%
        } else if (amount > 500) {
            return 0.027; // 2.7%
        } else {
            return 0.035; // 3.5%
        }
    }

}

