package com.spacetravel.service;
import com.spacetravel.model.Client;

public interface ClientService {
    void createClient(String name);
    Client getClient(long id);
}