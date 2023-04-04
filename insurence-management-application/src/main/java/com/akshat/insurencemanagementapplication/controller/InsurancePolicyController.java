package com.akshat.insurencemanagementapplication.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.akshat.insurencemanagementapplication.exception.ResourceNotFoundException;
import com.akshat.insurencemanagementapplication.model.Client;
import com.akshat.insurencemanagementapplication.model.InsurancePolicy;
import com.akshat.insurencemanagementapplication.repositories.InsurancePolicyRepository;
import com.akshat.insurencemanagementapplication.services.ClientService;

@Controller
@RequestMapping("/policies")
@Validated
public class InsurancePolicyController {

    @Autowired
    private InsurancePolicyRepository policyRepository;

    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public String getAllPolicies(Model model) {
        List<InsurancePolicy> policies = policyRepository.findAll();
        model.addAttribute("policies", policies);
        return "insurancePolicy/insurance-policy-all";
    }

    @GetMapping("/{id}")
    public String getPolicyById(@PathVariable Long id, Model model) throws ResourceNotFoundException {
        Optional<InsurancePolicy> policy = policyRepository.findById(id);
        model.addAttribute("policy", policy.orElseThrow(() -> new ResourceNotFoundException("Policy not found for this id :: " + id)));
        return "insurancePolicy/insurance-policy-details";
    }

    @GetMapping("/create")
    public String addPolicy(Model model) {
        InsurancePolicy policy = new InsurancePolicy();
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("policy", policy);
        model.addAttribute("clients", clients);
        model.addAttribute("allClients", true);
        return "insurancePolicy/create-insurance-policy";
    }

    @PostMapping("/create")
    public String createPolicy(@ModelAttribute("policy") @Valid InsurancePolicy policy, BindingResult result) {
        if (result.hasErrors()) {
            return "insurancePolicy/create-insurance-policy";
        }
        policyRepository.save(policy);
        return "redirect:/policies/";
    }

    @GetMapping("/{id}/update")
    public String editPolicy(@PathVariable Long id, Model model) throws ResourceNotFoundException {
        Optional<InsurancePolicy> policy = policyRepository.findById(id);
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("policy", policy.orElseThrow(() -> new ResourceNotFoundException("Policy not found for this id :: " + id)));
        model.addAttribute("clients", clients);
        return "insurancePolicy/update-insurance-policy";
    }

    @PostMapping("/{id}/update")
    public String updatePolicy(@PathVariable(value = "id") Long policyId,
                               @ModelAttribute("policy") @Valid InsurancePolicy policy, BindingResult result)
            throws ResourceNotFoundException {
        if (result.hasErrors()) {
            return "insurancePolicy/update-insurance-policy";
        }
        policy.setId(policyId);
        policyRepository.save(policy);
        return "redirect:/policies/";
    }

    @GetMapping("/{id}/delete")
    public String deletePolicy(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<InsurancePolicy> policy = policyRepository.findById(id);
        if (!policy.isPresent()) {
            throw new ResourceNotFoundException("Policy not found for this id :: " + id);
        }
        policyRepository.delete(policy.get());
        return "redirect:/policies/";
    }

}
