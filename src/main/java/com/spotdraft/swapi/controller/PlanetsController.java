package com.spotdraft.swapi.controller;

import com.spotdraft.swapi.config.ConfigProperties;
import com.spotdraft.swapi.service.PlanetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spotdraft.swapi.pojo.response.GenericResponse;
import com.spotdraft.swapi.pojo.response.PlanetsResponse;

/**
 * Planets controller class.
 */
@RestController
public class PlanetsController {

    @Autowired
    PlanetsService planetsService;

    /**
     * Sync all planets from SWAPI and save to database.
     * This is automatically done on application start up.
     *
     * @return Generic success/failure response.
     */
    @GetMapping("/sync/planets")
    public GenericResponse syncPlanetsAndSaveToDb() {
        return planetsService.syncPlanetsAndSaveToDb(ConfigProperties.SWAPI_BASE_URL + ConfigProperties.SWAPI_PLANETS_ENDPOINT);
    }

    /**
     * Fetch all planets or specific planet details from database.
     *
     * @param planetName Planet name or custom name.
     * @return List of planets.
     */
    @GetMapping("/planets")
    public PlanetsResponse fetchPlanets(@RequestParam(name = "name", required = false) String planetName) {
        return planetsService.fetchPlanets(planetName);
    }
}
