package com.spotdraft.swapi.service;

import com.spotdraft.swapi.pojo.response.FilmsResponse;
import com.spotdraft.swapi.pojo.response.GenericResponse;

/**
 * Films service interface.
 */
public interface FilmsService {

    /**
     * Sync films from SWAPI and save to database.
     *
     * @param url SWAPI URL.
     * @return Generic success/failure response.
     */
    GenericResponse syncFilmsAndSaveToDb(String url);

    /**
     * Fetch all films or a specific film by title/custom title from database.
     *
     * @param filmTitle Film title/custom title.
     * @return List of films.
     */
    FilmsResponse fetchFilms(String filmTitle);
}
