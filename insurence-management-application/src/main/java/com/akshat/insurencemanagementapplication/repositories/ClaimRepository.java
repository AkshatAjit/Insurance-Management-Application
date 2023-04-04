package com.akshat.insurencemanagementapplication.repositories;

import com.akshat.insurencemanagementapplication.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    // no additional methods needed
}
