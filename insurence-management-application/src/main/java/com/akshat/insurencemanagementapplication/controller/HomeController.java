package com.akshat.insurencemanagementapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.akshat.insurencemanagementapplication.services.HomeServices;

@Controller
public class HomeController {
    
    private final HomeServices homeServices;
    
    @Autowired
    public HomeController(HomeServices homeServices) {
        this.homeServices = homeServices;
    }
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("numClients", homeServices.getNumClients());
        model.addAttribute("numPolicies", homeServices.getNumPolicies());
        model.addAttribute("numClaims", homeServices.getNumClaims());
        return "home";
    }
    
    @GetMapping("/clients")
    public String clients() {
        return "redirect:/clients/";
    }
    
    @GetMapping("/claims")
    public String claims() {
        return "redirect:/claims/";
    }
    
    @GetMapping("/insurance-policies")
    public String insurancePolicies() {
        return "redirect:/policies/";
    }
}
