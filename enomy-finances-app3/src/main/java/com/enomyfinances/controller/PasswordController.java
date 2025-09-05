package com.enomyfinances.controller;

import com.enomyfinances.dao.UserDAO;
import com.enomyfinances.model.User;
import com.enomyfinances.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

@Controller
public class PasswordController {
    private static final Logger logger = LoggerFactory.getLogger(PasswordController.class);
    private static final Pattern PASSWORD_PATTERN =
        Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/change-password")
    public ModelAndView showChangePassword(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return new ModelAndView("redirect:/Login");
        }
        return new ModelAndView("change_password").addObject("user", user);
    }

    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/Login";
        }

        if (!validatePasswordChange(currentPassword, newPassword, confirmPassword, user, redirectAttributes)) {
            return "redirect:/change-password";
        }

        String newSalt = PasswordUtils.generateSalt();
        String newHashedPassword = PasswordUtils.hashPassword(newPassword, newSalt);
        
        boolean success = userDAO.updatePassword(user.getUserId(), newHashedPassword, newSalt);
        if (success) {
            logger.info("Password successfully changed for user ID: {}", user.getUserId());
            redirectAttributes.addFlashAttribute("successMessage", "Your password has been updated successfully.");
            return "redirect:/profile";
        } else {
            logger.error("Failed to update password for user ID: {}", user.getUserId());
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update password. Please try again later.");
            return "redirect:/change-password";
        }
    }

    private boolean validatePasswordChange(
            String currentPassword,
            String newPassword,
            String confirmPassword,
            User user,
            RedirectAttributes redirectAttributes) {

        if (!PASSWORD_PATTERN.matcher(newPassword).matches()) {
            redirectAttributes.addFlashAttribute("errorMessage", "New password must meet complexity requirements.");
            return false;
        }

        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "New password and confirm password do not match.");
            return false;
        }

        if (currentPassword.equals(newPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "New password must be different from the current password.");
            return false;
        }

        return true;
    }
}
