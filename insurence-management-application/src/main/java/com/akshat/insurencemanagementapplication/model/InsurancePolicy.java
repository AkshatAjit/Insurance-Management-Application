package com.akshat.insurencemanagementapplication.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "insurance_policies")
public class InsurancePolicy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(name = "policy_number")
    private String policyNumber;
    
    @NotBlank
    private String type;
    
    @Positive
    @Column(name = "coverage_amount")
    private double coverageAmount;
    
    @Positive
    private double premium;
    
    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @NotNull
    @Column(name = "end_date")
    private LocalDate endDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;
    
    // Constructors
    public InsurancePolicy() {
    }
    
    public InsurancePolicy(String policyNumber, String type, double coverageAmount, double premium, LocalDate startDate, LocalDate endDate, Client client) {
        this.policyNumber = policyNumber;
        this.type = type;
        this.coverageAmount = coverageAmount;
        this.premium = premium;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
    }
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPolicyNumber() {
        return policyNumber;
    }
    
    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public double getCoverageAmount() {
        return coverageAmount;
    }
    
    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }
    
    public double getPremium() {
        return premium;
    }
    
    public void setPremium(double premium) {
        this.premium = premium;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    // toString method
    @Override
    public String toString() {
        return "InsurancePolicy{" +
                "id=" + id +
                ", policyNumber='" + policyNumber + '\'' +
                ", type='" + type + '\'' +
                ", coverageAmount=" + coverageAmount +
                ", premium=" + premium +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", client=" + client +
                '}';
    }
}
