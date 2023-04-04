package com.akshat.insurencemanagementapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshat.insurencemanagementapplication.repositories.ClaimRepository;
import com.akshat.insurencemanagementapplication.repositories.ClientRepository;
import com.akshat.insurencemanagementapplication.repositories.InsurancePolicyRepository;

@Service
public class HomeServices {

    private final ClientRepository clientRepository;
    private final InsurancePolicyRepository policyRepository;
    private final ClaimRepository claimRepository;
    
    @Autowired
    public HomeServices(ClientRepository clientRepository,
                       InsurancePolicyRepository policyRepository, ClaimRepository claimRepository) {
        this.clientRepository = clientRepository;
        this.policyRepository = policyRepository;
        this.claimRepository = claimRepository;
    }
    
    public long getNumClients() {
        return clientRepository.count();
    }
    
    public long getNumPolicies() {
        return policyRepository.count();
    }
    
    public long getNumClaims() {
        return claimRepository.count();
    }
    
}
