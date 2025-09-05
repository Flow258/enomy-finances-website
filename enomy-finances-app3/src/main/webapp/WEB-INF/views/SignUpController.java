<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enomy-Finances Dashboard</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>">
    <style>
        /* Base Styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: system-ui, -apple-system, sans-serif;
        }

        body {
            background-color: #000;
            color: #fff;
        }

        /* Header Styles */
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 1rem;
            border-bottom: 1px solid #333;
        }

        .logo {
            display: flex;
            align-items: center;
            gap: 0.5rem;
            text-decoration: none;
            color: #fff;
        }

        .logo-circle {
            width: 32px;
            height: 32px;
            background-color: #22c55e;
            border-radius: 50%;
            position: relative;
        }

        /* Form Styles */
        .finance-form {
            background: #1f2937;
            padding: 2rem;
            border-radius: 0.5rem;
            margin-bottom: 2rem;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            color: #e5e7eb;
        }

        .form-group input,
        .form-group select {
            width: 100%;
            padding: 0.75rem;
            border-radius: 0.375rem;
            border: 1px solid #4b5563;
            background-color: #374151;
            color: #fff;
        }

        .form-group input:focus,
        .form-group select:focus {
            outline: none;
            border-color: #22c55e;
        }

        /* Currency Converter Styles */
        .currency-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 1rem;
        }

        .result-display {
            margin-top: 1rem;
            padding: 1rem;
            background-color: #374151;
            border-radius: 0.375rem;
            display: none;
        }

        /* Investment Calculator Styles */
        .investment-limits {
            margin: 1rem 0;
            padding: 1rem;
            background-color: #374151;
            border-radius: 0.375rem;
            font-size: 0.875rem;
        }

        .investment-projections {
            margin-top: 2rem;
        }

        /* Button Styles */
        .btn {
            padding: 0.75rem 1.5rem;
            border-radius: 0.375rem;
            border: none;
            cursor: pointer;
            font-weight: 500;
            transition: background-color 0.2s;
        }

        .btn-primary {
            background-color: #22c55e;
            color: #000;
        }

        .btn-primary:hover {
            background-color: #16a34a;
        }

        /* Error Styles */
        .error-message {
            color: #ef4444;
            font-size: 0.875rem;
            margin-top: 0.5rem;
            display: none;
        }

        /* Success Styles */
        .success-message {
            color: #22c55e;
            font-size: 0.875rem;
            margin-top: 0.5rem;
            display: none;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .currency-grid {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
    <!-- Header Section -->
    <header class="header">
        <a href="<c:url value='/'/>" class="logo">
            <div class="logo-circle"></div>
            <span>Enomy-Finances</span>
        </a>
        <div class="header-right">
            <a href="<c:url value='/profile'/>" class="btn btn-primary">Profile</a>
            <form action="<c:url value='/logout'/>" method="post" style="display: inline;">
                <button type="submit" class="btn">Logout</button>
            </form>
        </div>
    </header>

    <!-- Main Content -->
    <main class="container">
        <!-- Currency Converter Section -->
        <section class="finance-form">
            <h2>Currency Converter</h2>
            <form id="currencyForm" action="<c:url value='/convert'/>" method="post">
                <div class="currency-grid">
                    <div class="form-group">
                        <label for="fromCurrency">From Currency:</label>
                        <select id="fromCurrency" name="fromCurrency" required>
                            <option value="GBP">British Pound (GBP)</option>
                            <option value="USD">US Dollar (USD)</option>
                            <option value="EUR">Euro (EUR)</option>
                            <option value="BRL">Brazilian Real (BRL)</option>
                            <option value="JPY">Japanese Yen (JPY)</option>
                            <option value="TRY">Turkish Lira (TRY)</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="toCurrency">To Currency:</label>
                        <select id="toCurrency" name="toCurrency" required>
                            <option value="USD">US Dollar (USD)</option>
                            <option value="GBP">British Pound (GBP)</option>
                            <option value="EUR">Euro (EUR)</option>
                            <option value="BRL">Brazilian Real (BRL)</option>
                            <option value="JPY">Japanese Yen (JPY)</option>
                            <option value="TRY">Turkish Lira (TRY)</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="amount">Amount:</label>
                    <input type="number" id="amount" name="amount" min="300" max="5000" step="0.01" required>
                    <div class="investment-limits">
                        Transaction limits: Min 300 - Max 5000 of initial currency
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Convert Currency</button>
                <div id="conversionError" class="error-message"></div>
                <div id="conversionResult" class="success-message"></div>
            </form>
        </section>

        <!-- Investment Calculator Section -->
        <section class="finance-form">
            <h2>Investment Calculator</h2>
            <form id="investmentForm" action="<c:url value='/calculate-investment'/>" method="post">
                <div class="form-group">
                    <label for="investmentType">Investment Type:</label>
                    <select id="investmentType" name="investmentType" required>
                        <option value="basic">Basic Savings Plan</option>
                        <option value="plus">Savings Plan Plus</option>
                        <option value="managed">Managed Stock Investments</option>
                    </select>
                    <div id="investmentLimits" class="investment-limits"></div>
                </div>
                <div class="form-group">
                    <label for="initialAmount">Initial Investment (£):</label>
                    <input type="number" id="initialAmount" name="initialAmount" required>
                </div>
                <div class="form-group">
                    <label for="monthlyInvestment">Monthly Investment (£):</label>
                    <input type="number" id="monthlyInvestment" name="monthlyInvestment" required>
                </div>
                <button type="submit" class="btn btn-primary">Calculate Returns</button>
                <div id="investmentError" class="error-message"></div>
            </form>
            <div id="investmentProjections" class="investment-projections"></div>
        </section>
    </main>

    <script>
        // Investment limits update
        function updateInvestmentLimits() {
            const type = document.getElementById('investmentType').value;
            const limitsElement = document.getElementById('investmentLimits');
            
            const limits = {
                basic: 'Maximum investment per year: £20,000 | Minimum monthly: £50',
                plus: 'Maximum investment per year: £30,000 | Minimum monthly: £50 | Minimum initial: £300',
                managed: 'Unlimited yearly investment | Minimum monthly: £150 | Minimum initial: £1,000'
            };

            limitsElement.textContent = limits[type];
        }

        // Form validation
        document.getElementById('currencyForm').addEventListener('submit', function(e) {
            e.preventDefault();
            const amount = parseFloat(document.getElementById('amount').value);
            
            if (amount < 300 || amount > 5000) {
                document.getElementById('conversionError').style.display = 'block';
                document.getElementById('conversionError').textContent = 'Amount must be between 300 and 5000';
                return;
            }

            // Form submission logic would go here
            document.getElementById('conversionError').style.display = 'none';
            document.getElementById('conversionResult').style.display = 'block';
            document.getElementById('conversionResult').textContent = 'Conversion successful';
        });

        document.getElementById('investmentForm').addEventListener('submit', function(e) {
            e.preventDefault();
            const type = document.getElementById('investmentType').value;
            const initial = parseFloat(document.getElementById('initialAmount').value);
            const monthly = parseFloat(document.getElementById('monthlyInvestment').value);

            let error = '';
            
            switch(type) {
                case 'basic':
                    if (monthly < 50) error = 'Minimum monthly investment is £50';
                    break;
                case 'plus':
                    if (initial < 300) error = 'Minimum initial investment is £300';
                    if (monthly < 50) error = 'Minimum monthly investment is £50';
                    break;
                case 'managed':
                    if (initial < 1000) error = 'Minimum initial investment is £1000';
                    if (monthly < 150) error = 'Minimum monthly investment is £150';
                    break;
            }

            if (error) {
                document.getElementById('investmentError').style.display = 'block';
                document.getElementById('investmentError').textContent = error;
                return;
            }

            // Form submission logic would go here
            document.getElementById('investmentError').style.display = 'none';
        });

        // Initialize page
        document.addEventListener('DOMContentLoaded', function() {
            updateInvestmentLimits();
        });

        // Update investment limits when type changes
        document.getElementById('investmentType').addEventListener('change', updateInvestmentLimits);
    </script>
</body>
</html>