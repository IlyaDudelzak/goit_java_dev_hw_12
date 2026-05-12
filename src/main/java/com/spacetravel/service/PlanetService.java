package com.spacetravel.service;
import com.spacetravel.model.Planet;

public interface PlanetService {
    void createPlanet(String name);
    Planet getPlanet(long id);
}