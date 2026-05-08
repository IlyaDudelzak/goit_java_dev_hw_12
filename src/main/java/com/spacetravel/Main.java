package com.spacetravel;

import com.spacetravel.crud.ClientCrudService;
import com.spacetravel.model.Client;
import org.flywaydb.core.Flyway;

public class Main {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:./spacetravel", "sa", "")
                .load();
        flyway.migrate();

        ClientCrudService clientService = new ClientCrudService();

        Client newClient = new Client();
        newClient.setName("Mark Watney");
        clientService.save(newClient);

        System.out.println("Saved client ID: " + newClient.getId());

        Client found = clientService.findById(newClient.getId());
        System.out.println("Found client: " + found.getName());
    }
}