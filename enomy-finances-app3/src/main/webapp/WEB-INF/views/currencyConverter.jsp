<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Currency Converter</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>">
</head>
<body>
    <header>
        <h1>Currency Converter</h1>
    </header>

    <main>
        <form method="POST" action="<c:url value='/convertCurrency'/>">
            <div>
                <label for="fromCurrency">From Currency:</label>
                <select id="fromCurrency" name="fromCurrency" required>
                    <option value="GBP">British Pound (GBP)</option>
                    <option value="USD">US Dollar (USD)</option>
                    <option value="EUR">Euro (EUR)</option>
                </select>
            </div>
            <div>
                <label for="toCurrency">To Currency:</label>
                <select id="toCurrency" name="toCurrency" required>
                    <option value="USD">US Dollar (USD)</option>
                    <option value="GBP">British Pound (GBP)</option>
                    <option value="EUR">Euro (EUR)</option>
                </select>
            </div>
            <div>
                <label for="amount">Amount:</label>
                <input type="number" id="amount" name="amount" min="300" max="5000" step="0.01" required>
            </div>
            <button type="submit">Convert</button>
        </form>

        <c:if test="${not empty convertedAmount}">
            <div class="success-message">
                <p>Converted Amount: <fmt:formatNumber value="${convertedAmount}" type="number"/> ${toCurrency}</p>
            </div>
        </c:if>

        <div class="fees-section">
            <h2>Fees</h2>
            <table>
                <thead>
                    <tr>
                        <th>Initial Currency Amount</th>
                        <th>Fee</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Up to 500</td>
                        <td>3.5%</td>
                    </tr>
                    <tr>
                        <td>Over 500</td>
                        <td>2.7%</td>
                    </tr>
                    <tr>
                        <td>Over 1500</td>
                        <td>2.0%</td>
                    </tr>
                    <tr>
                        <td>Over 2500</td>
                        <td>1.5%</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>