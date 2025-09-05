<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/style_home.css">
</head>
<body>
    <header class="header">
        <a href="${pageContext.request.contextPath}/" class="logo">
            <div class="logo-circle"></div>
            <span>Enomy-Finances</span>
        </a>
        <nav class="nav-links">
            <a href="${pageContext.request.contextPath}/Dashboard" class="nav-link">Dashboard</a>
            <a href="${pageContext.request.contextPath}/profile" class="nav-link">Profile</a>
            <a href="${pageContext.request.contextPath}/logout" class="cta-button">Logout</a>
        </nav>
    </header>

    <section class="hero">
        <div class="hero-content">
            <div class="hero-text">
                <h1>Change Your Password</h1>
                <p>Regularly updating your password helps keep your account secure.</p>
            </div>
            
            <div class="form-container">
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">
                        ${successMessage}
                    </div>
                </c:if>
                
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">
                        ${errorMessage}
                    </div>
                </c:if>
                
                <form action="${pageContext.request.contextPath}/change-password" method="post">
                    <div class="form-group">
                        <label for="currentPassword">Current Password</label>
                        <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="newPassword">New Password</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword" 
                               pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" 
                               title="Password must be at least 8 characters long, include one uppercase, one lowercase, one number, and one special character (@$!%*?&)." 
                               required>
                    </div>
                    
                    <div class="form-group">
                        <label for="confirmPassword">Confirm New Password</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                        <small id="passwordMismatch" style="color: red; display: none;">Passwords do not match.</small>
                    </div>
                    
                    <div class="password-requirements">
                        <h3>Password Requirements:</h3>
                        <ul>
                            <li>Minimum 8 characters in length</li>
                            <li>At least one uppercase letter (A-Z)</li>
                            <li>At least one lowercase letter (a-z)</li>
                            <li>At least one digit (0-9)</li>
                            <li>At least one special character (@$!%*?&)</li>
                        </ul>
                    </div>
                    
                    <div class="form-group">
                        <button type="submit" class="btn-primary">Update Password</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
    
    <footer>
        <div class="footer-grid">
            <div class="footer-about">
                <a href="${pageContext.request.contextPath}/" class="logo">
                    <div class="logo-circle"></div>
                    <span>Enomy-Finances</span>
                </a>
                <p>At Enomy-Finances, we are dedicated to providing smart financial solutions to help you secure your future. Explore our range of investment and savings options today.</p>
            </div>
            <div class="footer-links">
                <h4>Quick Links</h4>
                <ul>
                    <li><a href="#">Features</a></li>
                    <li><a href="#">Plans</a></li>
                    <li><a href="#">About</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
            </div>
            <div class="footer-links">
                <h4>Resources</h4>
                <ul>
                    <li><a href="#">Help Center</a></li>
                    <li><a href="#">FAQs</a></li>
                    <li><a href="#">Blog</a></li>
                    <li><a href="#">Privacy Policy</a></li>
                </ul>
            </div>
            <div class="footer-links">
                <h4>Contact Us</h4>
                <ul>
                    <li><a href="mailto:support@enemy-finances.com">support@enemy-finances.com</a></li>
                    <li><a href="tel:+441234567890">+44 123 456 7890</a></li>
                    <li><a href="#">Live Chat</a></li>
                </ul>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2025 Enomy-Finances. All rights reserved.</p>
            <div class="footer-bottom-links">
                <a href="#">Terms of Service</a>
                <a href="#">Privacy Policy</a>
            </div>
        </div>
    </footer>
    
    <script>
    document.addEventListener('DOMContentLoaded', function() {
        const form = document.querySelector('form');
        const newPassword = document.getElementById('newPassword');
        const confirmPassword = document.getElementById('confirmPassword');
        const passwordMismatchMessage = document.getElementById('passwordMismatch');

        function validatePasswords() {
            if (newPassword.value !== confirmPassword.value) {
                passwordMismatchMessage.style.display = 'block';
                return false;
            } else {
                passwordMismatchMessage.style.display = 'none';
                return true;
            }
        }

        confirmPassword.addEventListener('input', validatePasswords);

        form.addEventListener('submit', function(event) {
            if (!validatePasswords()) {
                event.preventDefault();
                alert('New password and confirm password do not match!');
            }
        });
    });
    </script>
</body>
</html>
