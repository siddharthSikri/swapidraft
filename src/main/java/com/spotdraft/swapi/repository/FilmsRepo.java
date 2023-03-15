package com.spotdraft.swapi.repository;

import com.spotdraft.swapi.entity.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Films data access interface.
 */
@Repository
public interface FilmsRepo extends CrudRepository<Film, String> {

    /**
     * Fetch all films.
     *
     * @return List of films.
     */
    List<Film> findAll();

    /**
     * Fetch all films by title.
     *
     * @param filmTitle Film title.
     * @return List of films.
     */
    List<Film> findAllByTitle(String filmTitle);

    /**
     * Fetch all films by custom title.
     *
     * @param filmTitle Film custom title.
     * @return List of films.
     */
    List<Film> findAllByCustomTitle(String filmTitle);

    /**
     * Fetch film by id.
     *
     * @param id Film ID.
     * @return Film.
     */
    Optional<Film> findById(String id);
}
