package com.akshat.insurencemanagementapplication.model;

import java.time.LocalDate;

import javax.validation.constraints.*;

import jakarta.persistence.*;

@Entity
@Table(name = "claims")
public class Claim {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String claimNumber;
    
    @NotBlank
    private String description;
    
    @NotNull
    private LocalDate claimDate;
    
    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus;
    
    @ManyToOne
    @JoinColumn(name = "policy_id", nullable = false)
    private InsurancePolicy policy;
    
    // Constructors
    public Claim() {}
    
    public Claim(String claimNumber, String description, LocalDate claimDate, ClaimStatus claimStatus) {
        this.claimNumber = claimNumber;
        this.description = description;
        this.claimDate = claimDate;
        this.claimStatus = claimStatus;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public ClaimStatus getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(ClaimStatus claimStatus) {
        this.claimStatus = claimStatus;
    }

    public InsurancePolicy getPolicy() {
        return policy;
    }

    public void setPolicy(InsurancePolicy policy) {
        this.policy = policy;
    }
}
