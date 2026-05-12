package com.spacetravel;

import com.spacetravel.dao.ClientDao;
import com.spacetravel.dao.impl.ClientDaoImpl;
import com.spacetravel.service.ClientService;
import com.spacetravel.service.impl.ClientServiceImpl;

public class Main {
    public static void main(String[] args) {
        ClientDao clientDao = new ClientDaoImpl();

        ClientService clientService = new ClientServiceImpl(clientDao);

        try {
            clientService.createClient("Elon Musk");
            System.out.println("Client created successfully!");
        } catch (IllegalArgumentException e) {
            System.err.println("Validation failed: " + e.getMessage());
        }
    }
}