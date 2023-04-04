package com.akshat.insurencemanagementapplication.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.akshat.insurencemanagementapplication.model.Claim;
import com.akshat.insurencemanagementapplication.model.InsurancePolicy;
import com.akshat.insurencemanagementapplication.model.Client;
import com.akshat.insurencemanagementapplication.repositories.ClaimRepository;
import com.akshat.insurencemanagementapplication.repositories.InsurancePolicyRepository;
import com.akshat.insurencemanagementapplication.repositories.ClientRepository;

@Controller
@RequestMapping("/claims")
public class ClaimController {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    // Get all claims
    @GetMapping("/")
    public String getAllClaims(Model model) {
        List<Claim> claims = claimRepository.findAll();
        model.addAttribute("claims", claims);
        return "claim/claim-all";
    }

    // Get claim form
    @GetMapping("/create")
    public String getClaimForm(Model model) {
        List<InsurancePolicy> policies = insurancePolicyRepository.findAll();
        model.addAttribute("claim", new Claim());
        model.addAttribute("policies", policies);
        return "claim/create-claim-form";
    }

    // Create new claim
    @PostMapping("/create")
    public String createClaim(@Valid @ModelAttribute("claim") Claim claim, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.claim", bindingResult);
            redirectAttributes.addFlashAttribute("claim", claim);
            return "redirect:/claims/create";
        } else {
            // Fetch the policy using the provided policy ID
            InsurancePolicy policy = insurancePolicyRepository.findById(claim.getPolicy().getId()).orElse(null);
            if (policy == null) {
                // Policy not found, return an error message
                bindingResult.rejectValue("policy.id", "error.claim", "Invalid policy selected");
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.claim", bindingResult);
                redirectAttributes.addFlashAttribute("claim", claim);
                return "redirect:/claims/create";
            }
            
            // Set the policy for the claim
            claim.setPolicy(policy);
            
            claimRepository.save(claim);
            return "redirect:/claims/";
        }
    }


    // Get claim by ID
    @GetMapping("/{id}")
    public String getClaimById(@PathVariable("id") Long id, Model model) {
        Optional<Claim> claim = claimRepository.findById(id);
        if (claim.isPresent()) {
            Claim claimObj = claim.get();
            model.addAttribute("claim", claimObj);
            model.addAttribute("policyId", claimObj.getPolicy().getId());
        }
        return "claim/claim-details";
    }

    // Update claim
    @PostMapping("/{id}/update")
    public String updateClaim(@PathVariable("id") Long id, @ModelAttribute("claim") Claim claim) {
        claim.setId(id);
        claimRepository.save(claim);
        return "redirect:/claims";
    }

    // Get claim update form
    @GetMapping("/{id}/update")
    public String getClaimUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<Claim> claim = claimRepository.findById(id);
        List<InsurancePolicy> policies = insurancePolicyRepository.findAll();
        model.addAttribute("claim", claim.orElse(new Claim()));
        model.addAttribute("policies", policies);
        return "claim/update-claim-form";
    }

    // Delete claim
    @GetMapping("/{id}/delete")
    public String deleteClaim(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
    	claimRepository.deleteById(id);
    	redirectAttributes.addFlashAttribute("successMessage", "Claim deleted successfully");
    	return "redirect:/claims/";
    }
}
