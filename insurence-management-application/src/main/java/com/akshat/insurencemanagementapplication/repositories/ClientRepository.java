package com.akshat.insurencemanagementapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akshat.insurencemanagementapplication.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}