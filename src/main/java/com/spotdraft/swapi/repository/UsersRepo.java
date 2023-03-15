package com.spotdraft.swapi.repository;

import com.spotdraft.swapi.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Users data access interface.
 */
@Repository
public interface UsersRepo extends CrudRepository<User, String> {

    /**
     * Fetch user by id.
     *
     * @param id User ID.
     * @return User.
     */
    Optional<User> findById(String id);

    /**
     * Fetch all users.
     *
     * @return List of all users.
     */
    List<User> findAll();

}
