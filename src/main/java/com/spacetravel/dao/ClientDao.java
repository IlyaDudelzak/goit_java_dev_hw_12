package com.spacetravel.dao;
import com.spacetravel.model.Client;

import java.util.List;

public interface ClientDao {
    void save(Client client);
    Client findById(long id);
    List<Client> findAll();
    void update(Client client);
    void delete(Client client);
}