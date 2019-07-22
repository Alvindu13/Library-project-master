package com.library.web.controller;

import com.library.dao.model.Book;
import com.library.dao.model.Client;
import com.library.dao.repository.ClientRepository;
import com.library.svc.contracts.ClientSvc;
import com.library.web.exceptions.BookNotFoundException;
import com.library.web.exceptions.ClientNofFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ClientController {

    @Autowired
    ClientRepository repository;


    @Autowired
    ClientSvc svc;

    // Find
    @GetMapping("/clients")
    List<Client> findAll() {
        return repository.findAll();
    }

    // Save
    @PostMapping("/clients")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    Client newClient(@RequestBody Client newClient) {
        return repository.save(newClient);
    }


    // Find
    @GetMapping("/clients/{id}")
    Client findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ClientNofFoundException(id));
    }

    // Save or update
    @PutMapping("/clients/{id}")
    Client saveOrUpdate(@RequestBody Client newClient, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setFirstName(newClient.getFirstName());
                    x.setLastName(newClient.getLastName());
                    x.setMail(newClient.getMail());
                    x.setEncrytedPassword(newClient.getEncrytedPassword());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newClient.setId(id);
                    return repository.save(newClient);
                });
    }



}
