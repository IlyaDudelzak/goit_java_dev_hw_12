package com.spacetravel.service.impl;

import com.spacetravel.dao.ClientDao;
import com.spacetravel.model.Client;
import com.spacetravel.service.ClientService;

public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public void createClient(String name) {
        if (name == null || name.length() < 3 || name.length() > 200) {
            throw new IllegalArgumentException("Invalid client name");
        }

        Client client = new Client();
        client.setName(name);
        clientDao.save(client);
    }

    @Override
    public Client getClient(long id) {
        return clientDao.findById(id);
    }
}