package com.akshat.insurencemanagementapplication.services;

import com.akshat.insurencemanagementapplication.model.Claim;
import com.akshat.insurencemanagementapplication.repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public Optional<Claim> getClaimById(Long id) {
        return claimRepository.findById(id);
    }

    public Claim createClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    public Optional<Claim> updateClaim(Long id, Claim claim) {
        Optional<Claim> optionalClaim = claimRepository.findById(id);
        if (optionalClaim.isPresent()) {
            Claim existingClaim = optionalClaim.get();
            existingClaim.setClaimNumber(claim.getClaimNumber());
            existingClaim.setDescription(claim.getDescription());
            existingClaim.setClaimDate(claim.getClaimDate());
            existingClaim.setClaimStatus(claim.getClaimStatus());
            existingClaim.setPolicy(claim.getPolicy());

            Claim updatedClaim = claimRepository.save(existingClaim);
            return Optional.of(updatedClaim);
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteClaim(Long id) {
        Optional<Claim> optionalClaim = claimRepository.findById(id);
        if (optionalClaim.isPresent()) {
            Claim claim = optionalClaim.get();
            claimRepository.delete(claim);
            return true;
        } else {
            return false;
        }
    }

}
