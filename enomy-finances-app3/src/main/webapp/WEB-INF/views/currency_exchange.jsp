<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Currency Exchange</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
</head>

<style>
    #resultContainer.show {
        display: block !important;
    }
    
    .alert {
        margin-top: 20px;
    }
    
    /* Add some animation for better UX */
    .alert {
        transition: all 0.3s ease-in-out;
    }
</style>
<body class="bg-light">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card shadow">
                    <div class="card-header bg-primary text-white">
                        <h3 class="mb-0">Currency Exchange Calculator</h3>
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/exchange-rate" method="GET" id="exchangeForm">
                            <div class="mb-3">
                                <label for="fromCurrency" class="form-label">From Currency:</label>
                                <select class="form-select" id="fromCurrency" name="fromCurrency" required>
                                    <option value="USD">USD - US Dollar</option>
                                    <option value="EUR">EUR - Euro</option>
                                    <option value="GBP">GBP - British Pound</option>
                                    <option value="BRL">BRL - Brazilian Real</option>
                                    <option value="JPY">JPY - Japanese Yen</option>
                                    <option value="TRY">TRY - Turkish Lira</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="toCurrency" class="form-label">To Currency:</label>
                                <select class="form-select" id="toCurrency" name="toCurrency" required>
                                    <option value="USD">USD - US Dollar</option>
                                    <option value="EUR">EUR - Euro</option>
                                    <option value="GBP">GBP - British Pound</option>
                                    <option value="BRL">BRL - Brazilian Real</option>
                                    <option value="JPY">JPY - Japanese Yen</option>
                                    <option value="TRY">TRY - Turkish Lira</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="amount" class="form-label">Amount:</label>
                                <input type="number" class="form-control" id="amount" name="amount" step="0.01" min="0.01" required>
                            </div>

                            <button type="submit" class="btn btn-primary">Calculate Exchange</button>
                        </form>
                        
                        <div id="loadingIndicator" class="text-center mt-3" style="display: none;">
                            <div class="spinner-border text-primary" role="status">
                                <span class="visually-hidden">Loading...</span>
                            </div>
                        </div>

                        <div id="resultContainer" class="mt-4" style="display: none;">
                            <div class="alert alert-info">
                                <h4 class="alert-heading">Exchange Results</h4>
                                <div class="row">
                                    <div class="col-md-6">
                                        <p><strong>Original Amount:</strong> <span id="originalAmount"></span></p>
                                        <p><strong>Fee Amount:</strong> <span id="feeAmount"></span></p>
                                    </div>
                                    <div class="col-md-6">
                                        <p><strong>Net Amount:</strong> <span id="netAmount"></span></p>
                                        <p><strong>Converted Amount:</strong> <span id="convertedAmount"></span></p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="errorContainer" class="mt-4 alert alert-danger" style="display: none;">
                            <p class="mb-0">Error: <span id="errorMessage"></span></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#exchangeForm').on('submit', function(e) {
                e.preventDefault();
                
                $('#resultContainer').hide();
                $('#errorContainer').hide();
                $('#loadingIndicator').show();
                
                $.ajax({
                    url: $(this).attr('action'),
                    type: 'GET',
                    data: $(this).serialize(),
                    success: function(response) {
                        $('#loadingIndicator').hide();
                        if (response.error) {
                            showError(response.error);
                            return;
                        }

                        $('#resultContainer').show();
                        $('#originalAmount').text(formatCurrency(response.originalAmount, $('#fromCurrency').val()));
                        $('#feeAmount').text(formatCurrency(response.feeAmount, $('#fromCurrency').val()));
                        $('#netAmount').text(formatCurrency(response.netAmount, $('#fromCurrency').val()));
                        $('#convertedAmount').text(formatCurrency(response.convertedAmount, response.toCurrency));
                    },
                    error: function(xhr, status, error) {
                        $('#loadingIndicator').hide();
                        let errorMessage = 'Error calculating exchange rate.';
                        if (xhr.responseJSON && xhr.responseJSON.error) {
                            errorMessage = xhr.responseJSON.error;
                        }
                        showError(errorMessage);
                        console.error('Exchange calculation error:', error);
                    }
                });
            });

            $('#fromCurrency, #toCurrency').on('change', function() {
                let fromCurrency = $('#fromCurrency').val();
                let toCurrency = $('#toCurrency').val();
                
                if(fromCurrency === toCurrency) {
                    showError('Please select different currencies');
                    $(this).val($(this).find('option:not(:selected)').first().val());
                }
            });

            function showError(message) {
                $('#errorMessage').text(message);
                $('#errorContainer').show();
                $('#resultContainer').hide();
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
