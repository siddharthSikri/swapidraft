package com.spotdraft.swapi.service.impl;

import com.spotdraft.swapi.constants.Constants;
import com.spotdraft.swapi.entity.Film;
import com.spotdraft.swapi.entity.Planet;
import com.spotdraft.swapi.entity.User;
import com.spotdraft.swapi.pojo.request.Favourites;
import com.spotdraft.swapi.pojo.response.GenericResponse;
import com.spotdraft.swapi.enums.EntityTypeEnums;
import com.spotdraft.swapi.repository.FilmsRepo;
import com.spotdraft.swapi.repository.PlanetsRepo;
import com.spotdraft.swapi.repository.UsersRepo;
import com.spotdraft.swapi.service.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Favourites service implementation class.
 */
@Service
public class FavouritesServiceImpl implements FavouritesService {

    @Autowired
    FilmsRepo filmsRepo;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    PlanetsRepo planetsRepo;

    /**
     * Toggle favourites for films/planets and set custom title for films/custom name for planets.
     *
     * @param favReqBody Favourites request body.
     * @param entityType FILM/PLANET.
     * @param userId     User ID.
     * @param filmId     Film ID.
     * @param planetId   Planet ID.
     * @return Generic success/failure response.
     */
    @Override
    public GenericResponse markFavouriteAndSetCustomTitleOrName(Favourites favReqBody, EntityTypeEnums entityType, String userId, String filmId, String planetId) {
        Optional<User> userOpt = usersRepo.findById(userId);
        if (userOpt.isPresent() && Objects.nonNull(favReqBody)) {
            if (StringUtils.hasLength(filmId)
                    && !StringUtils.hasLength(planetId)
                    && EntityTypeEnums.FILM.equals(entityType)) {
                return markFavouriteFilmAndSetCustomTitle(userOpt.get(), favReqBody, userId, filmId);
            } else if (StringUtils.hasLength(planetId)
                    && !StringUtils.hasLength(filmId)
                    && EntityTypeEnums.PLANET.equals(entityType)) {
                return markFavouritePlanetAndSetCustomName(userOpt.get(), favReqBody, userId, planetId);
            } else {
                return new GenericResponse(true, Constants.INVALID_REQ_OR_PARAMS, null);
            }
        } else {
            return new GenericResponse(true, Constants.NO_USER_FOUND, null);
        }
    }

    /**
     * Toggle favourites for films and set custom title. Populate favourite list for users and films.
     *
     * @param user       User details.
     * @param favReqBody Favourites request body.
     * @param userId     User ID.
     * @param filmId     Film ID.
     * @return Generic success/failure response.
     */
    private GenericResponse markFavouriteFilmAndSetCustomTitle(User user, Favourites favReqBody, String userId, String filmId) {
        try {
            Set<String> userFavFilms = user.getFavouriteFilms();
            if (CollectionUtils.isEmpty(userFavFilms)){
                userFavFilms = new HashSet<>();
            }
            Optional<Film> filmOpt = filmsRepo.findById(filmId);
            if (filmOpt.isPresent()) {
                Film film = filmOpt.get();
                Set<String> favUserSet = film.getFavouriteUsers();
                if (CollectionUtils.isEmpty(favUserSet)){
                    favUserSet = new HashSet<>();
                }
                if (StringUtils.hasLength(favReqBody.getCustomFilmTitle())) {
                    film.setCustomTitle(favReqBody.getCustomFilmTitle());
                }
                if (Objects.nonNull(favReqBody.getFavourite())) {
                    if (Boolean.TRUE.equals(favReqBody.getFavourite())) {
                        film.setIsFavourite(true);
                        favUserSet.add(userId);
                        userFavFilms.add(filmId);
                    } else {
                        userFavFilms.remove(filmId);
                        favUserSet.remove(userId);
                        if (0 == favUserSet.size()) {
                            film.setIsFavourite(false);
                        }
                    }
                }
                film.setFavouriteUsers(favUserSet);
                film.setEdited(String.valueOf(System.currentTimeMillis()));
                user.setEdited(System.currentTimeMillis());
                user.setFavouriteFilms(userFavFilms);
                filmsRepo.save(film);
                usersRepo.save(user);
                return new GenericResponse(true, Constants.ACTION_SUCCESSFUL, null);
            } else {
                return new GenericResponse(true, Constants.VALID_FILM_ID, null);
            }
        } catch (Exception e) {
            return new GenericResponse(false, Constants.EXCEPTION_OCCURRED + e.getMessage(), null);
        }
    }

    /**
     * Toggle favourites for planets and set custom title. Populate favourite list for users and planets.
     *
     * @param user       User details.
     * @param favReqBody Favourites request body.
     * @param userId     User ID.
     * @param planetId   Planet ID.
     * @return Generic success/failure response.
     */
    private GenericResponse markFavouritePlanetAndSetCustomName(User user, Favourites favReqBody, String userId, String planetId) {
        try {
            Set<String> userFavPlanets = user.getFavouritePlanets();
            if (CollectionUtils.isEmpty(userFavPlanets)){
                userFavPlanets = new HashSet<>();
            }
            Optional<Planet> planetOpt = planetsRepo.findById(planetId);
            if (planetOpt.isPresent()) {
                Planet planet = planetOpt.get();
                Set<String> favUserSet = planet.getFavouriteUsers();
                if (CollectionUtils.isEmpty(favUserSet)){
                    favUserSet = new HashSet<>();
                }
                if (StringUtils.hasLength(favReqBody.getCustomPlanetName())) {
                    planet.setCustomName(favReqBody.getCustomPlanetName());
                }
                if (Objects.nonNull(favReqBody.getFavourite())) {
                    if (Boolean.TRUE.equals(favReqBody.getFavourite())) {
                        planet.setIsFavourite(true);
                        favUserSet.add(userId);
                        userFavPlanets.add(planetId);
                    } else {
                        userFavPlanets.remove(planetId);
                        favUserSet.remove(userId);
                        if (0 == favUserSet.size()) {
                            planet.setIsFavourite(false);
                        }
                    }
                }
                planet.setFavouriteUsers(favUserSet);
                planet.setEdited(String.valueOf(System.currentTimeMillis()));
                user.setFavouritePlanets(userFavPlanets);
                user.setEdited(System.currentTimeMillis());
                planetsRepo.save(planet);
                usersRepo.save(user);
                return new GenericResponse(true, Constants.ACTION_SUCCESSFUL, null);
            } else {
                return new GenericResponse(true, Constants.VALID_PLANET_ID, null);
            }
        } catch (Exception e) {
            return new GenericResponse(false, Constants.EXCEPTION_OCCURRED + e.getMessage(), null);
        }
    }
}
