<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login & Register</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/style_login_register.css">
    
</head>
<body>
    <header class="header">
        <a href="http://localhost:8080/enomy-finances-app3/" class="logo">
            <div class="logo-circle"></div>
            <span>Enomy-Finances</span>
        </a>
    </header>

    <div class="auth-container">
        <input type="checkbox" id="formToggle" class="form-toggle">
        
        <!-- Login Form -->
        <div class="auth-content login-form">
            <div class="auth-graphic">
                <div class="green-slash"></div>
            </div>
            <div class="auth-form">
                <h2>Login</h2>
                <c:if test="${not empty error}">
                    <p style="color: red;">${error}</p>
                </c:if>
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="form-group">
                        <label for="loginEmail">Email</label>
                        <input id="loginEmail" name="email" type="email" placeholder="Email" required>
                    </div>
                    <div class="form-group">
                        <label for="loginPassword">Password</label>
                        <input id="loginPassword" name="passwordhash" type="password" placeholder="Password" required>
                        <a href="#" class="forgot-link">Forgot password?</a>
                    </div>
                    <button type="submit" class="submit-button">Login</button>
                    <div class="register-prompt">
                        Don't have an account? 
                        <label for="formToggle" class="register-link">Register here</label>
                    </div>
                </form>
            </div>
        </div>

        <!-- Registration Form -->
        <div class="auth-content registration-form">
            <div class="auth-graphic">
                <div class="green-slash"></div>
            </div>
            <div class="auth-form">
                <label for="formToggle" class="back-to-login">
                    ← Back to Login
                </label>
                <h2>Register</h2>
                <form action="${pageContext.request.contextPath}/signup" method="post">
                    <div class="form-group">
                        <label for="registerFullName">Full Name</label>
                        <input id="registerFullName" name="fullName" type="text" placeholder="Full Name" required>
                    </div>
                    <div class="form-group">
                        <label for="registerEmail">Email</label>
                        <input id="registerEmail" name="email" type="email" placeholder="Email" required>
                    </div>
                    <div class="form-group">
                        <label for="registerPassword">Password</label>
                        <input id="registerPassword" name="passwordHash" type="password" placeholder="Password" required>
                    </div>
                    <div class="form-group">
                        <label for="registerRole">Role</label>
                        <select id="registerRole" name="role" required>
                            <option value="CLIENT">Client</option>
                            
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="twoFactor">Enable Two-Factor Authentication?</label>
                        <input id="twoFactor" name="twoFactorEnabled" type="checkbox">
                    </div>
                    <button type="submit" class="submit-button">Register</button>
                </form>
            </div>
        </div>
    </div>
</body>

</html>