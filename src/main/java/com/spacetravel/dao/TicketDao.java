package com.spacetravel.dao;

import com.spacetravel.model.Ticket;
import java.util.List;

public interface TicketDao {
    void save(Ticket ticket);
    Ticket findById(long id);
    void update(Ticket ticket);
    void delete(Ticket ticket);
    List<Ticket> findAll();
}