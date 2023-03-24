package com.fincalc.FinancialCalculator.Controllers;

import com.fincalc.FinancialCalculator.Models.CompoundCalculation;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.DecimalFormat;

@Controller
public class MainController {

    DecimalFormat df = new DecimalFormat("#.##");

    CompoundCalculation currentCompCalc = new CompoundCalculation();

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/compound-interest-calculator")
    public String showCompoundCalc() {
        return "calculator.html";
    }

    @PostMapping("/compound-interest-calculator")
    public void compoundCalc(@RequestParam double principal, @RequestParam double apr, @RequestParam double compound, @RequestParam double time, HttpServletResponse response) {

        double total = calculateCompound(principal, apr, compound, time);

        currentCompCalc.setPrincipal(principal);
        currentCompCalc.setApr(apr);
        currentCompCalc.setCompound(compound);
        currentCompCalc.setTime(time);

        currentCompCalc.setTotal(total);

        String interest_rounded = df.format(total-principal);


        currentCompCalc.setTotalInterest(Double.parseDouble(interest_rounded));

        try {
            response.sendRedirect("/result");
        } catch (IOException e) {e.printStackTrace();}
    }

    @GetMapping("/result")
    public String showResult(Model model) {
        model.addAttribute("currentCompCalc", currentCompCalc);
        return "result.html";
    }



    private double calculateCompound(double principal, double apr, double compound, double time) {
        double apr_in_perc = apr/100;
        String result_rounded = df.format((principal * Math.pow((1 + (apr_in_perc/compound)),(compound*time))));
        return Double.parseDouble(result_rounded);
    }
}
