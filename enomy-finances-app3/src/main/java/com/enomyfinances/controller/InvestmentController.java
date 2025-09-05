package com.enomyfinances.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.enomyfinances.model.InvestmentRequest;
import com.enomyfinances.model.InvestmentResult;
import com.enomyfinances.service.InvestmentService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/investment")
public class InvestmentController {

    private static final Logger logger = LoggerFactory.getLogger(InvestmentController.class);
    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    
    @GetMapping("/CurrencyExchange")
    public ModelAndView showCurrencyExchange() {
        ModelAndView mav = new ModelAndView("currency_exchange");
        mav.addObject("message", "Currency Exchange Page");
        return mav;
    }
    
    @GetMapping("/ShowCalculator")
    public String showCalculator(Model model) {
        model.addAttribute("investmentRequest", new InvestmentRequest());
        return "investment-calculator";
    }

    @GetMapping("/calculate/{investmentType}/{initialAmount}/{monthlyAmount}")
    @ResponseBody
    public List<InvestmentResult> calculate(@PathVariable double initialAmount, 
    										@PathVariable double monthlyAmount, 
    										@PathVariable String investmentType) {
        
        System.out.println("Work");
        System.out.println("Work");
        System.out.println("Work");        
        System.out.println("Work");
        
        
        // Validate input
        if (initialAmount < 0 || monthlyAmount < 0) {
            logger.error("Invalid input: Initial or monthly amount cannot be negative.");
            throw new IllegalArgumentException("Initial and monthly amounts must be non-negative.");
        }

        List<InvestmentResult> results = investmentService.calculateInvestment(new InvestmentRequest(investmentType, initialAmount, monthlyAmount));
        
        // Debugging Output
        if (results.isEmpty()) {
            logger.warn("Investment calculation returned no results.");
        } else {
            logger.info("Investment calculation successful. Returning {} results.", results.size());
        }

        return results;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleInvalidInput(IllegalArgumentException e) {
        logger.error("Investment calculation error: {}", e.getMessage());
        return Map.of("error", e.getMessage());
    }
}
