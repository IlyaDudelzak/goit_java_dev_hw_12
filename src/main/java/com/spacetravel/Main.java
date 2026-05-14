package com.spacetravel;

import com.spacetravel.dao.ClientDao;
import com.spacetravel.dao.PlanetDao;
import com.spacetravel.dao.TicketDao;
import com.spacetravel.dao.impl.ClientDaoImpl;
import com.spacetravel.dao.impl.PlanetDaoImpl;
import com.spacetravel.dao.impl.TicketDaoImpl;
import com.spacetravel.model.Client;
import com.spacetravel.model.Planet;
import com.spacetravel.model.Ticket;
import com.spacetravel.service.ClientService;
import com.spacetravel.service.TicketService;
import com.spacetravel.service.impl.ClientServiceImpl;
import com.spacetravel.service.impl.TicketServiceImpl;
import org.flywaydb.core.Flyway;

public class Main {
    public static void main(String[] args) {
        // 1. Запуск Flyway миграций (накатит V1, V2, а также новые V3 и V4)
        System.out.println("Running database migrations...");
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:./spacetravel", "sa", "")
                .load();
        flyway.migrate();
        System.out.println("Migrations applied successfully!\n");

        // 2. Инициализация DAO слоев
        ClientDao clientDao = new ClientDaoImpl();
        PlanetDao planetDao = new PlanetDaoImpl();
        TicketDao ticketDao = new TicketDaoImpl();

        // 3. Инициализация Сервисов (внедряем зависимости через конструктор)
        ClientService clientService = new ClientServiceImpl(clientDao);
        TicketService ticketService = new TicketServiceImpl(ticketDao, clientDao, planetDao);

        System.out.println("--- Testing Client Service ---");
        // Тест создания клиента
        try {
            clientService.createClient("Elon Musk");
            System.out.println("Client 'Elon Musk' created successfully!");
        } catch (IllegalArgumentException e) {
            System.err.println("Validation failed: " + e.getMessage());
        }

        System.out.println("\n--- Testing Ticket Service (Valid Scenario) ---");
        // Успешный тест: берем существующего клиента из базы (например, ID = 1) и планеты (EARTH -> MARS)
        try {
            Client existingClient = clientDao.findById(1L);
            Planet fromPlanet = planetDao.findById("EARTH");
            Planet toPlanet = planetDao.findById("MARS");

            if (existingClient != null && fromPlanet != null && toPlanet != null) {
                Ticket ticket = new Ticket();
                ticket.setClient(existingClient);
                ticket.setFromPlanet(fromPlanet);
                ticket.setToPlanet(toPlanet);

                ticketService.createTicket(ticket);
                System.out.println("Ticket created successfully! New Ticket ID: " + ticket.getId());
            } else {
                System.err.println("Error: Test data from migration V2/V4 not found.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Ticket creation failed: " + e.getMessage());
        }

        System.out.println("\n--- Testing Ticket Service (Invalid Scenario - Fake Planet) ---");
        // Негативный тест: пробуем подсунуть несуществующую планету PLUTO
        try {
            Client existingClient = clientDao.findById(1L);
            Planet fromPlanet = planetDao.findById("EARTH");

            // Создаем объект планеты, которой нет в базе данных
            Planet fakePlanet = new Planet();
            fakePlanet.setId("PLUTO");
            fakePlanet.setName("Pluto");

            Ticket invalidTicket = new Ticket();
            invalidTicket.setClient(existingClient);
            invalidTicket.setFromPlanet(fromPlanet);
            invalidTicket.setToPlanet(fakePlanet);

            ticketService.createTicket(invalidTicket);
            System.out.println("Oops! Ticket with fake planet was saved (this shouldn't happen).");
        } catch (IllegalArgumentException e) {
            System.out.println("Success! Validation correctly blocked the fake planet: " + e.getMessage());
        }
    }
}