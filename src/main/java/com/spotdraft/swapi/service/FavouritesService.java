package com.spotdraft.swapi.service;

import com.spotdraft.swapi.pojo.request.Favourites;
import com.spotdraft.swapi.pojo.response.GenericResponse;
import com.spotdraft.swapi.enums.EntityTypeEnums;

/**
 * Favourites service interface.
 */
public interface FavouritesService {

    /**
     * Toggle favourite film/planet and set custom name/custom title.
     * Populate favourite lists for users, planets and films accordingly.
     *
     * @param favReqBody Favourites request body.
     * @param entityType FILM/PLANET.
     * @param userId User ID.
     * @param filmId Film ID.
     * @param planetId Planet ID.
     * @return Generic success/failure response.
     */
    GenericResponse markFavouriteAndSetCustomTitleOrName(Favourites favReqBody, EntityTypeEnums entityType, String userId, String filmId, String planetId);
}
