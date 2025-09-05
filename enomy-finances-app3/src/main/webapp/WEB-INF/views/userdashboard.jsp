<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enomy-Finances Dashboard</title>
    
    
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
            gap: 0.75rem;
            text-decoration: none;
            color: white;
        }

        .logo-circle {
            width: 32px;
            height: 32px;
            background-color: #22c55e;
            border-radius: 50%;
            position: relative;
        }

        .logo-circle::after {
            content: "";
            position: absolute;
            width: 16px;
            height: 2px;
            background-color: black;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%) rotate(-45deg);
        }

        /* Tab Styles */
        .tab-container {
            margin: 2rem auto;
            max-width: 800px;
        }

        .tabs {
            display: flex;
            gap: 1rem;
            margin-bottom: 1rem;
        }

        .tab {
            padding: 0.75rem 1.5rem;
            background-color: #1f2937;
            border-radius: 0.5rem;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        .tab.active {
            background-color: #22c55e;
            color: #000;
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

        /* Result Styles */
        .result-display {
            margin-top: 1rem;
            padding: 1rem;
            background-color: #374151;
            border-radius: 0.375rem;
        }

        .result-display p {
            margin: 0.5rem 0;
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
        
        /* Additional styles */
        .result-section {
            background: #2d3748;
            padding: 1.5rem;
            border-radius: 0.5rem;
            margin-bottom: 1rem;
        }

        .result-section h5 {
            color: #22c55e;
            margin-bottom: 1rem;
            font-size: 1.1rem;
        }

        .loading {
            display: none;
            text-align: center;
            padding: 1rem;
        }

        .loading-spinner {
            border: 3px solid #f3f3f3;
            border-top: 3px solid #22c55e;
            border-radius: 50%;
            width: 24px;
            height: 24px;
            animation: spin 1s linear infinite;
            margin: 0 auto;
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }

        .error-message {
            color: #ef4444;
            background: #fee2e2;
            border: 1px solid #ef4444;
            padding: 0.75rem;
            border-radius: 0.375rem;
            margin-bottom: 1rem;
            display: none;
        }

        .info-tooltip {
            position: relative;
            display: inline-block;
            margin-left: 0.5rem;
            cursor: help;
        }

        .info-tooltip::after {
            content: "ⓘ";
            color: #22c55e;
        }

        .info-tooltip .tooltip-text {
            visibility: hidden;
            width: 200px;
            background-color: #1f2937;
            color: #fff;
            text-align: center;
            padding: 0.5rem;
            border-radius: 0.375rem;
            position: absolute;
            z-index: 1;
            bottom: 125%;
            left: 50%;
            transform: translateX(-50%);
            opacity: 0;
            transition: opacity 0.3s;
        }

        .info-tooltip:hover .tooltip-text {
            visibility: visible;
            opacity: 1;
        }

        .chart-container {
            margin-top: 2rem;
            height: 300px;
            background: #2d3748;
            border-radius: 0.5rem;
            padding: 1rem;
        }
        
        .nav-links {
            display: flex;
            gap: 2rem;
            align-items: center;
        }

        .nav-link {
            color: #fff;
            text-decoration: none;
            font-size: 0.9rem;
            transition: color 0.2s;
        }

        .nav-link:hover {
            color: #22c55e;
        }

        .cta-button {
            background-color: #22c55e;
            color: black;
            padding: 0.75rem 1.5rem;
            border-radius: 50px;
            text-decoration: none;
            font-weight: 500;
            transition: background-color 0.2s;
        }

        .cta-button:hover {
            background-color: #16a34a;
        }
        
        
        .result-section {
            background: #1f2937;
            padding: 1rem;
            border-radius: 0.5rem;
            margin-top: 1.5rem;
        }

        .result-section h5 {
            margin-bottom: 1rem;
            color: #22c55e;
        }
    </style>
</head>
<body>
    <header class="header">
        <a href="${pageContext.request.contextPath}/LoginHome" class="logo">
            <div class="logo-circle"></div>
            <span>Enomy-Finances</span>
        </a>
        <nav class="nav-links">
            <a href="${pageContext.request.contextPath}/Profile" class="nav-link">Profile</a>
            <a href="${pageContext.request.contextPath}/logout" class="cta-button">Logout</a>
        </nav>
    </header>
    
    <div class="tab-container">
        <div class="tabs">
            <div class="tab active" data-tab="currency">Currency Exchange</div>
            <div class="tab" data-tab="investment">Investment Calculator</div>
        </div>

         <!-- Currency Exchange Form -->
    <div id="currencyTab" class="tab-content">
        <form action="${pageContext.request.contextPath}/convertCurrency" method="POST" id="exchangeForm">
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
            <div class="form-group">
                <label for="amount">Amount:</label>
                <input type="number" id="amount" name="amount" min="300" max="5000" step="0.01" required>
            </div>
            
            <!-- Hidden Input for Debugging -->
            <input type="hidden" id="exchangeRate" name="exchangeRate">
            
            <button type="submit" class="btn btn-primary" name="convertButton">Convert</button>
        </form>
    </div>

    <!-- Exchange Results -->
    <div id="currencyTab-resultContainer" class="result-section" style="display: none;">
        <h5>Exchange Results</h5>
        <p><strong>Original Amount:</strong> <span id="originalAmount"></span></p>
        <p><strong>Fee Amount:</strong> <span id="feeAmount"></span></p>
        <p><strong>Fee Percentage:</strong> <span id="feePercentage"></span></p>
        <p><strong>Net Amount:</strong> <span id="netAmount"></span></p>
        <p><strong>Exchange Rate:</strong> <span id="exchangeRate"></span></p>
        <p><strong>Converted Amount:</strong> <span id="convertedAmount"></span></p>
    </div>

<!-- Error Display -->
<div id="currencyTab-errorContainer" class="mt-4 alert alert-danger" style="display: none;">
    <p class="mb-0">Error: <span id="errorMessage"></span></p>
</div>


        <!-- Investment Calculator Form -->
        <div id="investmentTab" class="tab-content" style="display: none;">
    <div class="error-message" style="display: none;"></div>
    <div class="loading" style="display: none;">
        <div class="loading-spinner"></div>
        <p>Calculating investment projections...</p>
    </div>
    
    <form id="investmentForm" class="finance-form" action="${pageContext.request.contextPath}/investment/calculate" method="post">
        <div class="form-group">
            <label for="investmentType">Investment Type:</label>
            <select id="investmentType" name="investmentType" required>
                <option value="basic">Basic Savings Plan</option>
                <option value="plus">Savings Plan Plus</option>
                <option value="managed">Managed Stock Investments</option>
            </select>
        </div>
        <div class="form-group">
            <label for="initialAmount">Initial Investment Amount (£):</label>
            <input type="number" id="initialAmount" name="initialAmount" min="0" step="0.01" required>
        </div>
        <div class="form-group">
            <label for="monthlyAmount">Monthly Investment Amount (£):</label>
            <input type="number" id="monthlyAmount" name="monthlyAmount" min="50" step="0.01" required>
        </div>
        <button type="submit" class="btn btn-primary">Calculate Returns</button>
    </form>

    <div id="investmentResult" class="result-display" style="display: none;">
        <div id="investmentDetails">
         <div class="result-section">
                    <h5 id="resultYear1"></h5>
                    <p id="resultTotalInvested1"><strong>Total Invested:</strong> £${result.totalInvested.toFixed(2)}</p>
                    <p id="resultValueRange1"><strong>Projected Value Range:</strong> £${result.minReturn.toFixed(2)} to £${result.maxReturn.toFixed(2)}</p>
                    <p id="resultTotalFees1"><strong>Total Fees:</strong> £${result.fees.toFixed(2)}</p>
                    <p id="resultTax1"><strong>Estimated Tax:</strong> £${result.minTax.toFixed(2)} to £${result.maxTax.toFixed(2)}</p>
                    <p id="resultPotentialReturn1"><strong>Potential Return:</strong> ${minReturnPercentage}% to ${maxReturnPercentage}%</p>
                </div>
                
                <div class="result-section">
                    <h5 id="resultYear5"></h5>
                    <p id="resultTotalInvested5"><strong>Total Invested:</strong> £${result.totalInvested.toFixed(2)}</p>
                    <p id="resultValueRange5"><strong>Projected Value Range:</strong> £${result.minReturn.toFixed(2)} to £${result.maxReturn.toFixed(2)}</p>
                    <p id="resultTotalFees5"><strong>Total Fees:</strong> £${result.fees.toFixed(2)}</p>
                    <p id="resultTax5"><strong>Estimated Tax:</strong> £${result.minTax.toFixed(2)} to £${result.maxTax.toFixed(2)}</p>
                    <p id="resultPotentialReturn5"><strong>Potential Return:</strong> ${minReturnPercentage}% to ${maxReturnPercentage}%</p>
                </div>
                
                <div class="result-section">
                    <h5 id="resultYear10"></h5>
                    <p id="resultTotalInvested10"><strong>Total Invested:</strong> £${result.totalInvested.toFixed(2)}</p>
                    <p id="resultValueRange10"><strong>Projected Value Range:</strong> £${result.minReturn.toFixed(2)} to £${result.maxReturn.toFixed(2)}</p>
                    <p id="resultTotalFees10"><strong>Total Fees:</strong> £${result.fees.toFixed(2)}</p>
                    <p id="resultTax10"><strong>Estimated Tax:</strong> £${result.minTax.toFixed(2)} to £${result.maxTax.toFixed(2)}</p>
                    <p id="resultPotentialReturn10"><strong>Potential Return:</strong> ${minReturnPercentage}% to ${maxReturnPercentage}%</p>
                </div>
                
        <c:forEach items="${results}" var="result">
                  <div class="result-section">
                    <h5> ${result.years} Year Projection</h5>
                    <p><strong>Total Invested:</strong> £${result.totalInvested}</p>
                    <p><strong>Projected Value Range:</strong> £${result.minReturn} to £${result.maxReturn}</p>
                    <p><strong>Total Fees:</strong> £${result.fees}</p>
                    <p><strong>Estimated Tax:</strong> £${result.minTax} to £${result.maxTax}</p>
                    <p><strong>Potential Return:</strong> % to %</p>
                </div>
                </c:forEach>
                </div>
    </div>
</div>

    <script>
 // Investment Plans Configuration
    const INVESTMENT_LIMITS = {
        basic: {
            minMonthly: 50,
            maxYearly: 20000,
            minInitial: 0,
            rates: { min: 0.012, max: 0.024, fee: 0.0025 }
        },
        plus: {
            minMonthly: 50,
            maxYearly: 30000,
            minInitial: 300,
            rates: { min: 0.03, max: 0.055, fee: 0.003 }
        },
        managed: {
            minMonthly: 150,
            minInitial: 1000,
            rates: { min: 0.04, max: 0.23, fee: 0.013 }
        }
    };

    // Validate Investment Inputs
    function validateInvestment(type, initial, monthly) {
        const limits = INVESTMENT_LIMITS[type];
        const errors = [];

        if (initial < limits.minInitial) {
            errors.push(`Minimum initial investment is £${limits.minInitial}`);
        }
        if (monthly < limits.minMonthly) {
            errors.push(`Minimum monthly investment is £${limits.minMonthly}`);
        }
        if (limits.maxYearly && (monthly * 12) > limits.maxYearly) {
            errors.push(`Maximum yearly investment is £${limits.maxYearly}`);
        }

        return errors;
    }

    // Compound Interest Calculation
    function calculateCompoundReturn(initial, monthly, rate, months) {
        const monthlyRate = rate / 12;
        let balance = initial;
        const monthlyData = [];

        for (let i = 0; i < months; i++) {
            balance = (balance + monthly) * (1 + monthlyRate);
            monthlyData.push(balance);
        }

        return {
            finalBalance: balance,
            monthlyData: monthlyData
        };
    }

    // Tax Calculation
    function calculateTax(type, profit) {
        if (type === 'basic') return 0;

        let tax = 0;
        if (type === 'plus') {
            tax = profit > 12000 ? (profit - 12000) * 0.1 : 0;
        } else if (type === 'managed') {
            if (profit > 40000) {
                tax += (profit - 40000) * 0.2;
                tax += (40000 - 12000) * 0.1;
            } else if (profit > 12000) {
                tax += (profit - 12000) * 0.1;
            }
        }
        return tax;
    }

    // Investment Calculation Logic
    function calculateInvestmentResults(type, initial, monthly) {
        const limits = INVESTMENT_LIMITS[type];
        const periods = [1, 5, 10];
        const results = {};

        periods.forEach(years => {
            const months = years * 12;
            const totalInvested = initial + (monthly * months);

            const minCalc = calculateCompoundReturn(initial, monthly, limits.rates.min, months);
            const maxCalc = calculateCompoundReturn(initial, monthly, limits.rates.max, months);

            const fees = totalInvested * limits.rates.fee * months;
            const minReturn = minCalc.finalBalance - fees;
            const maxReturn = maxCalc.finalBalance - fees;

            const minTax = calculateTax(type, minReturn - totalInvested);
            const maxTax = calculateTax(type, maxReturn - totalInvested);

            results[years] = {
                totalInvested,
                minReturn: minReturn - minTax,
                maxReturn: maxReturn - maxTax,
                fees,
                minTax,
                maxTax,
                monthlyDataMin: minCalc.monthlyData,
                monthlyDataMax: maxCalc.monthlyData
            };
        });

        return results;
    }

    // Display Investment Results
    function displayResults(results) {
    	
    	console.log(results);
    	
        const resultContainer = document.getElementById('investmentDetails');
        let html = '';
		
        document.getElementById("resultYear1").textContent = results["0"].years + " year Projection";
        document.getElementById("resultYear5").textContent = results["1"].years + " years Projection";
        document.getElementById("resultYear10").textContent = results["2"].years + " years Projection";
        
        document.getElementById("resultTotalInvested1").textContent = "Total Invested: £"+results["0"].totalInvested;
        document.getElementById("resultTotalInvested5").textContent = "Total Invested: £"+results["1"].totalInvested;
        document.getElementById("resultTotalInvested10").textContent = "Total Invested: £"+results["2"].totalInvested;
        
        document.getElementById("resultValueRange1").textContent = "Projected Value Range: £"+results["0"].maxReturn.toFixed(2) + " to £"+results["0"].minReturn.toFixed(2);
        document.getElementById("resultValueRange5").textContent = "Projected Value Range: £"+results["1"].maxReturn.toFixed(2) + " to £"+results["1"].minReturn.toFixed(2);
        document.getElementById("resultValueRange10").textContent = "Projected Value Range: £"+results["2"].maxReturn.toFixed(2) + " to £"+results["2"].minReturn.toFixed(2);
            
        document.getElementById("resultTotalFees1").textContent = "Total Fees: £"+results["0"].fees.toFixed(2);
        document.getElementById("resultTotalFees5").textContent = "Total Fees: £"+results["1"].fees.toFixed(2);
        document.getElementById("resultTotalFees10").textContent = "Total Fees: £"+results["2"].fees.toFixed(2);
        
        document.getElementById("resultTax1").textContent = "Estimated Tax: £"+results["0"].minTax.toFixed(2) + " to £"+results["0"].maxTax.toFixed(2);
        document.getElementById("resultTax5").textContent = "Estimated Tax: £"+results["1"].minTax.toFixed(2) + " to £"+results["1"].maxTax.toFixed(2);
        document.getElementById("resultTax10").textContent = "Estimated Tax: £"+results["2"].minTax.toFixed(2) + " to £"+results["2"].maxTax.toFixed(2);
           
    	
        const minReturnPercentage = (((results["0","1","2"].minReturn - results["0","1","2"].totalInvested) / results["0","1","2"].totalInvested) * 100).toFixed(1);
        const maxReturnPercentage = (((results["0","1","2"].maxReturn - results["0","1","2"].totalInvested) / results["0","1","2"].totalInvested) * 100).toFixed(1);

        document.getElementById("resultPotentialReturn1").textContent = "Potential Return: %"+minReturnPercentage + " to %"+maxReturnPercentage;
        document.getElementById("resultPotentialReturn5").textContent = "Potential Return: %"+minReturnPercentage + " to %"+maxReturnPercentage;
        document.getElementById("resultPotentialReturn10").textContent = "Potential Return: %"+minReturnPercentage + " to %"+maxReturnPercentage;
        
        document.getElementById('investmentResult').style.display = 'block';

        // **Add a delay of 1 second (1000ms) before hiding the loading message**
        setTimeout(() => {
            document.querySelector('.loading').style.display = 'none';
        }, 1000);
        
        /*
        Object.values(results).forEach((result) => {
            const minReturnPercentage = (((result.minReturn - result.totalInvested) / result.totalInvested) * 100).toFixed(1);
            const maxReturnPercentage = (((result.maxReturn - result.totalInvested) / result.totalInvested) * 100).toFixed(1);
			
            console.log(result);
            
            document.getElementById("resultYear1").textContent = result.years + "yearProjection";
            document.getElementById("resultYear5").textContent = result.years + "yearProjection";
           
           
            html += `
                <div class="result-section">
                    <h5>${result.years} Year${result.years > 1 ? 's' : ''} Projection</h5>
                    <p><strong>Total Invested:</strong> £${result.totalInvested.toFixed(2)}</p>
                    <p><strong>Projected Value Range:</strong> £${result.minReturn.toFixed(2)} to £${result.maxReturn.toFixed(2)}</p>
                    <p><strong>Total Fees:</strong> £${result.fees.toFixed(2)}</p>
                    <p><strong>Estimated Tax:</strong> £${result.minTax.toFixed(2)} to £${result.maxTax.toFixed(2)}</p>
                    <p><strong>Potential Return:</strong> ${minReturnPercentage}% to ${maxReturnPercentage}%</p>
                </div>`;
                
        });
        */
        
   	/*
        resultContainer.innerHTML = html;
       */ 
       
        document.getElementById('investmentResult').style.display = 'block';
        
        
    }

    // Form Event Listeners
    document.addEventListener('DOMContentLoaded', () => {
        const investmentForm = document.getElementById('investmentForm');
        const errorDisplay = document.querySelector('.error-message');
        const loadingDisplay = document.querySelector('.loading');

        // Tab Switching
        document.querySelectorAll('.tab').forEach(tab => {
            tab.addEventListener('click', () => {
                document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
                tab.classList.add('active');

                document.querySelectorAll('.tab-content').forEach(content => {
                    content.style.display = 'none';
                });
                document.getElementById(tab.dataset.tab + 'Tab').style.display = 'block';
            });
        });

        // Investment Form Handling
        investmentForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            errorDisplay.style.display = 'none';
            loadingDisplay.style.display = 'block';

            const type = document.getElementById('investmentType').value;
            const initial = parseFloat(document.getElementById('initialAmount').value);
            const monthly = parseFloat(document.getElementById('monthlyAmount').value);

            const errors = validateInvestment(type, initial, monthly);

            if (errors.length > 0) {
                errorDisplay.textContent = errors.join('. ');
                errorDisplay.style.display = 'block';
                loadingDisplay.style.display = 'none';
                return;
            }
			
            const fetchResult = await fetch("./investment/calculate/"+type+"/"+initial+"/"+monthly);
            const results = await fetchResult.json(); 
            displayResults(results);
            
            
        });

        // Dynamic Form Updates Based on Investment Type
        document.getElementById('investmentType').addEventListener('change', (e) => {
            const limits = INVESTMENT_LIMITS[e.target.value];
            const monthlyInput = document.getElementById('monthlyAmount');
            const initialInput = document.getElementById('initialAmount');

            monthlyInput.min = limits.minMonthly;
            initialInput.min = limits.minInitial;

            monthlyInput.placeholder = `Min: £${limits.minMonthly}`;
            initialInput.placeholder = `Min: £${limits.minInitial}`;
        });
    });
	
   

    </script>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
$(document).ready(function() {
    $('#exchangeForm').on('submit', function(e) {
        e.preventDefault(); // Prevent form reload

        $('#currencyTab-resultContainer').hide();
        $('#currencyTab-errorContainer').hide();
        $('#currencyTab-loadingIndicator').show();

        $.ajax({
            url: $(this).attr('action'),
            type: 'POST',
            data: $(this).serialize(),
            success: function(response) {
                $('#currencyTab-loadingIndicator').hide();
                if (response.error) {
                    showError(response.error);
                    return;
                }
                
                $('#currencyTab-resultContainer').show();
                $('#originalAmount').text(formatCurrency(response.originalAmount, $('#fromCurrency').val()));
                $('#feeAmount').text(formatCurrency(response.feeAmount, $('#fromCurrency').val()));
                $('#feePercentage').text((response.feePercentage * 100).toFixed(2) + "%");
                $('#netAmount').text(formatCurrency(response.netAmount, $('#fromCurrency').val()));
                $('#exchangeRate').text(response.exchangeRate.toFixed(4));
                $('#convertedAmount').text(formatCurrency(response.convertedAmount, response.toCurrency));
            },
            error: function(xhr, status, error) {
                $('#currencyTab-loadingIndicator').hide();
                let errorMessage = 'Error converting currency.';
                if (xhr.responseJSON && xhr.responseJSON.error) {
                    errorMessage = xhr.responseJSON.error;
                }
                showError(errorMessage);
                console.error('Exchange conversion error:', error);
            }
        });
    });

    // Hide exchange result when switching to investment tab
    $('.tab').on('click', function() {
        if ($(this).attr('data-tab') === 'investment') {
            $('#currencyTab-resultContainer').hide();
        }
    });

    function showError(message) {
        $('#currencyTab-errorMessage').text(message);
        $('#currencyTab-errorContainer').show();
        $('#currencyTab-resultContainer').hide();
    }

    function formatCurrency(amount, currency) {
        return new Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: currency
        }).format(amount);
    }
});
</script>
    
</body>
</html>

