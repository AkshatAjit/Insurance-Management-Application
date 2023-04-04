package com.akshat.insurencemanagementapplication.controller;

import com.akshat.insurencemanagementapplication.model.Client;
import com.akshat.insurencemanagementapplication.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/")
    public String getAllClients(Model model) {
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "client/client-all";
    }

    @GetMapping("/{id}")
    public String getClientById(@PathVariable Long id, Model model) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            model.addAttribute("client", client.get());
            return "client/client-details";
        } else {
            return "404";
        }
    }

    @GetMapping("/create")
    public String showAddClientForm(Client client) {
        return "client/create-client-form";
    }

    @PostMapping("/create")
    public String addClient(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "client/create-client-form";
        }
        clientRepository.save(client);
        return "redirect:/clients/";
    }

    @GetMapping("/update/{id}")
    public String showUpdateClientForm(@PathVariable Long id, Model model) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            model.addAttribute("client", client.get());
            return "client/update-client-form";
        } else {
            return "404";
        }
    }

    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable Long id, @Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            client.setId(id);
            return "client/update-client-form";
        }
        clientRepository.save(client);
        return "redirect:/clients/";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return "redirect:/clients/";
    }
}
