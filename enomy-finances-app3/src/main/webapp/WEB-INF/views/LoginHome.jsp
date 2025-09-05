<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Enomy-Finances</title>
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
            <a href="${pageContext.request.contextPath}/Profile" class="cta-button">Profile</a>
        </nav>
    </header>

    <section class="hero">
        <div class="hero-content">
            <div class="hero-text">
                <h1>Smart Financial Solutions for Your Future</h1>
                <p>Take control of your financial journey with our comprehensive suite of investment and savings tools. Start building your wealth today.</p>
                <a href="${pageContext.request.contextPath}/register" class="cta-button">Get Started</a>
            </div>
            <div class="hero-image">
                <!-- Placeholder for hero image/graphic -->
            </div>
        </div>
    </section>

    <section class="features">
        <div class="features-grid">
            <div class="feature-card">
                <div class="feature-icon">📈</div>
                <h3>Investment Growth</h3>
                <p>Access diverse investment opportunities with our professionally managed portfolios.</p>
            </div>
            <div class="feature-card">
                <div class="feature-icon">🔒</div>
                <h3>Secure Savings</h3>
                <p>Keep your savings secure with our state-of-the-art security measures and insurance.</p>
            </div>
            <div class="feature-card">
                <div class="feature-icon">📊</div>
                <h3>Financial Analytics</h3>
                <p>Track your financial progress with detailed analytics and personalized insights.</p>
            </div>
        </div>
    </section>

    <section class="plans">
        <div class="section-header">
            <h2>Choose Your Plan</h2>
            <p>Select the perfect plan that aligns with your financial goals</p>
        </div>
        <div class="plans-grid">
            <div class="plan-card">
                <h3>Basic Savings</h3>
                <div class="plan-price">1.2% - 2.4%</div>
                <ul class="plan-features">
                    <li>Monthly investment from £30</li>
                    <li>No initial deposit required</li>
                    <li>Tax-free earnings</li>
                    <li>24/7 Support</li>
                </ul>
                <a href="${pageContext.request.contextPath}/register" class="cta-button">Get Started</a>
            </div>
            <div class="plan-card">
                <h3>Savings Plus</h3>
                <div class="plan-price">3% - 5.5%</div>
                <ul class="plan-features">
                    <li>Monthly investment from £50</li>
                    <li>£300 initial deposit</li>
                    <li>Tax benefits up to £12,000</li>
                    <li>Priority Support</li>
                </ul>
                <a href="${pageContext.request.contextPath}/register" class="cta-button">Get Started</a>
            </div>
            <div class="plan-card">
                <h3>Managed Stocks</h3>
                <div class="plan-price">4% - 23%</div>
                <ul class="plan-features">
                    <li>Monthly investment from £150</li>
                    <li>£1,000 initial deposit</li>
                    <li>Professional management</li>
                    <li>Dedicated Advisor</li>
                </ul>
                <a href="${pageContext.request.contextPath}/register" class="cta-button">Get Started</a>
            </div>
        </div>
    </section>

    <section class="testimonials">
        <div class="section-header">
            <h2>What Our Clients Say</h2>
            <p>Join thousands of satisfied customers who have transformed their financial future</p>
        </div>
        <div class="testimonials-grid">
            <div class="testimonial-card">
                <p class="testimonial-content">"Enomy-Finances provided me with the right investment strategy that helped me achieve my financial goals effortlessly!"</p>
                <div class="testimonial-author">
                    <div class="author-avatar"></div>
                    <div class="author-info">
                        <h4>Sarah T.</h4>
                        <p>Savings Plus Member</p>
                    </div>
                </div>
            </div>
            <div class="testimonial-card">
                <p class="testimonial-content">"The professional management and dedicated support have made investing stress-free and rewarding."</p>
                <div class="testimonial-author">
                    <div class="author-avatar"></div>
                    <div class="author-info">
                        <h4>James M.</h4>
                        <p>Managed Stocks Member</p>
                    </div>
                </div>
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
                    <li><a href="${pageContext.request.contextPath}/features">Features</a></li>
                    <li><a href="${pageContext.request.contextPath}/plans">Plans</a></li>
                    <li><a href="${pageContext.request.contextPath}/about">About</a></li>
                    <li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
                </ul>
            </div>
            <div class="footer-links">
                <h4>Resources</h4>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/help">Help Center</a></li>
                    <li><a href="${pageContext.request.contextPath}/faq">FAQs</a></li>
                    <li><a href="${pageContext.request.contextPath}/blog">Blog</a></li>
                    <li><a href="${pageContext.request.contextPath}/privacy">Privacy Policy</a></li>
                </ul>
            </div>
            <div class="footer-links">
                <h4>Contact Us</h4>
                <ul>
                    <li><a href="mailto:support@enomy-finances.com">support@enomy-finances.com</a></li>
                    <li><a href="tel:+441234567890">+44 123 456 7890</a></li>
                    <li><a href="${pageContext.request.contextPath}/chat">Live Chat</a></li>
                </ul>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2025 Enomy-Finances. All rights reserved.</p>
            <div class="footer-bottom-links">
                <a href="${pageContext.request.contextPath}/terms">Terms of Service</a>
                <a href="${pageContext.request.contextPath}/privacy">Privacy Policy</a>
            </div>
        </div>
    </footer>
</body>
</html>