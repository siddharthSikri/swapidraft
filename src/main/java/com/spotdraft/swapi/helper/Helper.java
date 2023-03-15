package com.spotdraft.swapi.helper;

import com.spotdraft.swapi.entity.Film;
import com.spotdraft.swapi.entity.Planet;
import com.spotdraft.swapi.pojo.response.swapi.SwapiFilmsResponse;
import com.spotdraft.swapi.pojo.response.swapi.SwapiPlanetsResponse;
import com.spotdraft.swapi.repository.FilmsRepo;
import com.spotdraft.swapi.repository.PlanetsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Helper functions class.
 */
@Service
public class Helper {

    @Autowired
    FilmsRepo filmsRepo;

    @Autowired
    PlanetsRepo planetsRepo;

    /**
     * Save films and planets to database after sync from SWAPI.
     *
     * @param swapiFilmsResponse   Films API response from SWAPI.
     * @param swapiPlanetsResponse Planets API response from SWAPI.
     */
    public void saveEntitiesToDb(SwapiFilmsResponse swapiFilmsResponse, SwapiPlanetsResponse swapiPlanetsResponse) {
        if (Objects.nonNull(swapiFilmsResponse) && Objects.isNull(swapiPlanetsResponse)) {
            for (Film film : swapiFilmsResponse.getFilmResults()) {
                filmsRepo.save(film);
            }
        }
        if (Objects.nonNull(swapiPlanetsResponse) && Objects.isNull(swapiFilmsResponse)) {
            for (Planet planet : swapiPlanetsResponse.getPlanetResults()) {
                planetsRepo.save(planet);
            }
        }
    }
}
