package com.spotdraft.swapi.repository;

import com.spotdraft.swapi.entity.Planet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Planets data access interface.
 */
@Repository
public interface PlanetsRepo extends CrudRepository<Planet, String> {

    /**
     * Fetch all planets.
     *
     * @return List of planets.
     */
    List<Planet> findAll();

    /**
     * Fetch all planets by name.
     *
     * @param planetName Planet name.
     * @return List of planets.
     */
    List<Planet> findAllByName(String planetName);

    /**
     * Fetch all planets by custom name.
     *
     * @param planetName Planet custom name.
     * @return List of planets.
     */
    List<Planet> findAllByCustomName(String planetName);

    /**
     * Fetch planet by id.
     *
     * @param planetId Planet ID.
     * @return Planet.
     */
    Optional<Planet> findById(String planetId);
}
