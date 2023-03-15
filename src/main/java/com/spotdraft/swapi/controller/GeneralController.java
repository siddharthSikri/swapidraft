package com.spotdraft.swapi.controller;

import com.spotdraft.swapi.config.ConfigProperties;
import com.spotdraft.swapi.pojo.response.GenericResponse;
import com.spotdraft.swapi.repository.FilmsRepo;
import com.spotdraft.swapi.repository.PlanetsRepo;
import com.spotdraft.swapi.repository.UsersRepo;
import com.spotdraft.swapi.service.FilmsService;
import com.spotdraft.swapi.service.PlanetsService;
import com.spotdraft.swapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * General controller class.
 */
@RestController
public class GeneralController {

    @Autowired
    FilmsService filmsService;

    @Autowired
    FilmsRepo filmsRepo;

    @Autowired
    PlanetsService planetsService;

    @Autowired
    PlanetsRepo planetsRepo;

    @Autowired
    UsersService usersService;

    @Autowired
    UsersRepo usersRepo;

    /**
     * Health check API.
     *
     * @return Generic success/failure response.
     */
    @GetMapping("/health")
    public GenericResponse healthCheck() {
        return new GenericResponse(true, "Greetings from SwapiDraft!", null);
    }

    /**
     * Tasks to be executed after dependency injection. These methods delete any previous data, sync all data from SWAPI and then create users.
     */
    @PostConstruct
    public void syncDatabase() {
        filmsRepo.deleteAll();
        planetsRepo.deleteAll();
        usersRepo.deleteAll();
        filmsService.syncFilmsAndSaveToDb(ConfigProperties.SWAPI_BASE_URL + ConfigProperties.SWAPI_FILMS_ENDPOINT);
        planetsService.syncPlanetsAndSaveToDb(ConfigProperties.SWAPI_BASE_URL + ConfigProperties.SWAPI_PLANETS_ENDPOINT);
        usersService.createUserAndSaveToDb(null);
    }
}
