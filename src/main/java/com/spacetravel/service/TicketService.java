package com.spacetravel.service;

import com.spacetravel.model.Ticket;

public interface TicketService {
    void createTicket(Ticket ticket);
    Ticket getTicket(long id);
}