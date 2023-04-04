package com.akshat.insurencemanagementapplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akshat.insurencemanagementapplication.exception.ResourceNotFoundException;
import com.akshat.insurencemanagementapplication.model.Client;
import com.akshat.insurencemanagementapplication.model.InsurancePolicy;
import com.akshat.insurencemanagementapplication.repositories.InsurancePolicyRepository;

@Service
@Transactional
public class InsurancePolicyService {
    
    @Autowired
    private InsurancePolicyRepository policyRepository;

    public List<InsurancePolicy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public Optional<InsurancePolicy> getPolicyById(Long id) {
        return policyRepository.findById(id);
    }

    public InsurancePolicy createPolicy(InsurancePolicy policy) {
        return policyRepository.save(policy);
    }

    public InsurancePolicy updatePolicy(Long policyId, InsurancePolicy policyDetails) throws ResourceNotFoundException {
        InsurancePolicy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found with id " + policyId));

        policy.setPolicyNumber(policyDetails.getPolicyNumber());
        policy.setType(policyDetails.getType());
        policy.setCoverageAmount(policyDetails.getCoverageAmount());
        policy.setPremium(policyDetails.getPremium());
        policy.setStartDate(policyDetails.getStartDate());
        policy.setEndDate(policyDetails.getEndDate());
        policy.setClient(policyDetails.getClient());

        return policyRepository.save(policy);
    }

    public void deletePolicy(Long id) {
    	InsurancePolicy policy = policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found for this id :: " + id));

    	policyRepository.delete(policy);
    }

}
