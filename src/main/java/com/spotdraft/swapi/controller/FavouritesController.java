package com.spotdraft.swapi.controller;

import com.spotdraft.swapi.pojo.request.Favourites;
import com.spotdraft.swapi.pojo.response.GenericResponse;
import com.spotdraft.swapi.enums.EntityTypeEnums;
import com.spotdraft.swapi.service.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Favourites controller class.
 */
@RestController
public class FavouritesController {

    @Autowired
    FavouritesService favouritesService;

    /**
     * API to toggle favourite and set custom name/title for a film/planet.
     *
     * @param favReqBody Request body.
     * @param entityType FILM/PLANET
     * @param userId     User ID.
     * @param filmId     Film ID.
     * @param planetId   Planet ID.
     * @return Generic success/failure response.
     */
    @PutMapping("/favourites")
    public GenericResponse markFavouriteAndSetCustomTitleOrName(
            @RequestBody Favourites favReqBody,
            @RequestParam(name = "entity_type") EntityTypeEnums entityType,
            @RequestParam(name = "user_id") String userId,
            @RequestParam(name = "film_id", required = false) String filmId,
            @RequestParam(name = "planet_id", required = false) String planetId) {
        return favouritesService.markFavouriteAndSetCustomTitleOrName(favReqBody, entityType, userId, filmId, planetId);
    }
}
