package com.spotdraft.swapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.spotdraft.swapi.constants.Constants;
import com.spotdraft.swapi.entity.Film;
import com.spotdraft.swapi.helper.Helper;
import com.spotdraft.swapi.repository.FilmsRepo;
import com.spotdraft.swapi.service.FilmsService;
import com.spotdraft.swapi.utilities.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.spotdraft.swapi.pojo.FilmResponse;
import com.spotdraft.swapi.pojo.response.FilmsResponse;
import com.spotdraft.swapi.pojo.response.GenericResponse;
import com.spotdraft.swapi.pojo.response.swapi.SwapiFilmsResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Films service implementation class.
 */
@Service
public class FilmsServiceImpl implements FilmsService {

    @Autowired
    WebUtils webUtils;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Helper helper;

    @Autowired
    FilmsRepo filmsRepo;

    @Autowired
    Gson gson;

    /**
     * Sync from SWAPI and save in films collection.
     *
     * @param url SWAPI URL.
     * @return Generic success/failure response.
     */
    @Override
    public GenericResponse syncFilmsAndSaveToDb(String url) {
        try {
            String response = webUtils.get(url);
            SwapiFilmsResponse swapiFilmsResponse = objectMapper.readValue(response, SwapiFilmsResponse.class);
            helper.saveEntitiesToDb(swapiFilmsResponse, null);
            if (null == swapiFilmsResponse.getNext()) {
                return new GenericResponse(true, Constants.FILMS_SYNCED, null);
            } else {
                syncFilmsAndSaveToDb(swapiFilmsResponse.getNext());
            }
            return new GenericResponse(true, Constants.FILMS_SYNCED, null);
        } catch (Exception e) {
            return new GenericResponse(false, Constants.EXCEPTION_OCCURRED + e.getMessage(), null);
        }
    }

    /**
     * Fetch all films or specific film based on title/custom title.
     *
     * @param filmTitle Film title/custom title.
     * @return List of films.
     */
    @Override
    public FilmsResponse fetchFilms(String filmTitle) {
        FilmsResponse filmsResponse = new FilmsResponse();
        try {
            List<Film> filmList;
            List<FilmResponse> filmResponseList = new ArrayList<>();
            if (StringUtils.hasLength(filmTitle)) {
                filmList = fetchFilmsByTitleOrCustomTitle(filmTitle);
            } else {
                filmList = filmsRepo.findAll();
            }
            for (Film film : filmList) {
                FilmResponse filmResponse = mapFilmResponse(film);
                if (StringUtils.hasLength(film.getCustomTitle())) {
                    filmResponse.setCustomTitle(film.getCustomTitle());
                }
                filmResponseList.add(filmResponse);
            }
            filmsResponse.setFilms(filmResponseList);
            filmsResponse.setCount(filmResponseList.size());
            filmsResponse.setSuccess(true);
            filmsResponse.setMessage(Constants.FILMS_FETCHED);
            return filmsResponse;
        } catch (Exception e) {
            filmsResponse.setSuccess(false);
            filmsResponse.setMessage(Constants.EXCEPTION_OCCURRED + e.getMessage());
            return filmsResponse;
        }
    }

    /**
     * Map SWAPI film response to required API response.
     *
     * @param film SWAPI Film details.
     * @return Film details wrt API response.
     */
    private FilmResponse mapFilmResponse(Film film) {
        FilmResponse filmResponse = new FilmResponse();
        try {
            filmResponse = objectMapper.readValue(gson.toJson(film), FilmResponse.class);
            return filmResponse;
        } catch (Exception e) {
            filmResponse.setSuccess(false);
            filmResponse.setMessage(Constants.EXCEPTION_OCCURRED + e.getMessage());
            return filmResponse;
        }
    }

    /**
     * Fetch film by title/custom title.
     *
     * @param filmTitle Film title.
     * @return List of films.
     */
    private List<Film> fetchFilmsByTitleOrCustomTitle(String filmTitle) {
        List<Film> filmList = filmsRepo.findAllByCustomTitle(filmTitle);
        if (CollectionUtils.isEmpty(filmList)) {
            filmList = filmsRepo.findAllByTitle(filmTitle);
        }
        return filmList;
    }
}

