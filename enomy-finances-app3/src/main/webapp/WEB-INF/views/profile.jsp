<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %> <!-- Enable session handling -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/style_home.css">
    <style>
        .profile-details {
            background-color: #000;
            border-radius: 8px;
            padding: 25px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        
        .profile-actions {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
            margin-top: 20px;
        }
        
        .btn {
            display: inline-block;
            padding: 12px 20px;
            border-radius: 4px;
            font-weight: 600;
            text-decoration: none;
            text-align: center;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        
        .btn-primary {
            background-color: #2962ff;
            color: white !important; /* Ensure text color remains white */
            border: none;
        }
        
        .btn-primary:hover {
            background-color: #1e56e3;
        }
        
        /* Remove any potential conflicting styles */
        a.btn {
            pointer-events: auto !important;
            opacity: 1 !important;
        }
    </style>
</head>
<body>
    <header class="header">
        <a href="${pageContext.request.contextPath}/" class="logo">
            <div class="logo-circle"></div>
            <span>Enomy-Finances</span>
        </a>
        <nav class="nav-links">
            <a href="${pageContext.request.contextPath}/Dashboard" class="nav-link">Dashboard</a>
            <a href="${pageContext.request.contextPath}/logout" class="cta-button">Logout</a>
        </nav>
    </header>

    <section class="hero">
        <div class="hero-content">
            <div class="hero-text">
                <h1>Welcome, ${user.fullName}!</h1>
                <p>Manage your account and explore your financial journey.</p>
            </div>
            <div class="profile-details">
                <h2>User Information</h2>
                <p><strong>Email:</strong> ${user.email}</p>
                <p><strong>Role:</strong> ${user.role}</p>
                <p><strong>Join Date:</strong> ${user.createdAt}</p>
                <p><strong>Status:</strong> ${user.status}</p>
                
                <div class="profile-actions">
                    <!-- Using both link and form button for maximum compatibility -->
                    <a href="${pageContext.request.contextPath}/change-password" class="btn btn-primary" id="changePasswordLink">Change Password</a>
                    
                    <!-- Alternative button approach using form submission -->
                    <form action="${pageContext.request.contextPath}/change-password" method="get" style="display: inline;">
                        <button type="submit" class="btn btn-primary" id="changePasswordButton">Change Password (Alt)</button>
                    </form>
                </div>
                
                <!-- JavaScript fallback for direct navigation -->
                <script>
                    document.getElementById('changePasswordLink').addEventListener('click', function(e) {
                        e.preventDefault();
                        window.location.href = '${pageContext.request.contextPath}/change-password';
                    });
                </script>
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
</body>
</html>