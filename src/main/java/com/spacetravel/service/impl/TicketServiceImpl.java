package com.spacetravel.service.impl;

import com.spacetravel.dao.ClientDao;
import com.spacetravel.dao.PlanetDao;
import com.spacetravel.dao.TicketDao;
import com.spacetravel.model.Ticket;
import com.spacetravel.service.TicketService;

public class TicketServiceImpl implements TicketService {
    private final TicketDao ticketDao;
    private final ClientDao clientDao;
    private final PlanetDao planetDao;

    public TicketServiceImpl(TicketDao ticketDao, ClientDao clientDao, PlanetDao planetDao) {
        this.ticketDao = ticketDao;
        this.clientDao = clientDao;
        this.planetDao = planetDao;
    }

    @Override
    public void createTicket(Ticket ticket) {
        // 1. Валідація на null
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }
        if (ticket.getClient() == null) {
            throw new IllegalArgumentException("Ticket client cannot be null");
        }
        if (ticket.getFromPlanet() == null || ticket.getToPlanet() == null) {
            throw new IllegalArgumentException("Departure and destination planets cannot be null");
        }

        // 2. Валідація на існування в БД
        if (clientDao.findById(ticket.getClient().getId()) == null) {
            throw new IllegalArgumentException("Client does not exist in the database");
        }
        if (planetDao.findById(ticket.getFromPlanet().getId()) == null) {
            throw new IllegalArgumentException("Departure planet does not exist in the database");
        }
        if (planetDao.findById(ticket.getToPlanet().getId()) == null) {
            throw new IllegalArgumentException("Destination planet does not exist in the database");
        }

        ticketDao.save(ticket);
    }

    @Override
    public Ticket getTicket(long id) {
        return ticketDao.findById(id);
    }
}