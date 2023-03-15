package com.spotdraft.swapi.service;

import com.spotdraft.swapi.pojo.response.GenericResponse;
import com.spotdraft.swapi.pojo.response.PlanetsResponse;

/**
 * Planets service interface.
 */
public interface PlanetsService {

    /**
     * Sync planets from SWAPI and save to database.
     *
     * @param url SWAPI URL.
     * @return Generic success/failure response.
     */
    GenericResponse syncPlanetsAndSaveToDb(String url);

    /**
     * Fetch all planets or a specific planet based on name/custom name from database.
     *
     * @param planetName Planet name/custom name.
     * @return List of planets.
     */
    PlanetsResponse fetchPlanets(String planetName);
}
