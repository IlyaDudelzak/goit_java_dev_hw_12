package com.spacetravel.service.impl;

import com.spacetravel.dao.PlanetDao;
import com.spacetravel.model.Planet;
import com.spacetravel.service.PlanetService;

public class PlanetServiceImpl implements PlanetService {
    private final PlanetDao planetDao;

    public PlanetServiceImpl(PlanetDao planetDao) {
        this.planetDao = planetDao;
    }

    @Override
    public void createPlanet(String name) {
        if (name == null || name.length() < 3 || name.length() > 200) {
            throw new IllegalArgumentException("Invalid planet name");
        }

        Planet planet = new Planet();
        planet.setName(name);
        planetDao.save(planet);
    }

    @Override
    public Planet getPlanet(long id) {
        return planetDao.findById(id);
    }
}