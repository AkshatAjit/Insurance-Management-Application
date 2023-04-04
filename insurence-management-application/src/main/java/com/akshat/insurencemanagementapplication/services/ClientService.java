package com.akshat.insurencemanagementapplication.services;

import com.akshat.insurencemanagementapplication.exception.ResourceNotFoundException;
import com.akshat.insurencemanagementapplication.model.Client;
import com.akshat.insurencemanagementapplication.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long clientId) {
        return clientRepository.findById(clientId);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long clientId, Client clientDetails) throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));

        client.setName(clientDetails.getName());
        client.setDateOfBirth(clientDetails.getDateOfBirth());
        client.setAddress(clientDetails.getAddress());
        client.setContactInfo(clientDetails.getContactInfo());

        final Client updatedClient = clientRepository.save(client);
        return updatedClient;
    }

    public void deleteClient(Long clientId) throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));

        clientRepository.delete(client);
    }
}
