package com.spotdraft.swapi.controller;

import com.spotdraft.swapi.config.ConfigProperties;
import com.spotdraft.swapi.service.FilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spotdraft.swapi.pojo.response.FilmsResponse;
import com.spotdraft.swapi.pojo.response.GenericResponse;

/**
 * Films controller class.
 */
@RestController
public class FilmsController {

    @Autowired
    FilmsService filmsService;

    /**
     * Sync all films from SWAPI and save to database.
     * This is automatically done on application start up.
     *
     * @return Generic success/failure response.
     */
    @GetMapping("/sync/films")
    public GenericResponse syncFilmsAndSaveToDb() {
        return filmsService.syncFilmsAndSaveToDb(ConfigProperties.SWAPI_BASE_URL + ConfigProperties.SWAPI_FILMS_ENDPOINT);
    }

    /**
     * Fetch all films or specific film details from database.
     *
     * @param filmTitle Film title/custom film title.
     * @return List of films.
     */
    @GetMapping("/films")
    public FilmsResponse fetchFilms(
            @RequestParam(name = "title", required = false) String filmTitle){
        return filmsService.fetchFilms(filmTitle);
    }
}
