package com.spotdraft.swapi.service;

import com.spotdraft.swapi.pojo.response.GenericResponse;
import com.spotdraft.swapi.pojo.response.UsersResponse;

/**
 * Users service interface.
 */
public interface UsersService {

    /**
     * Create mock users and save to database.
     *
     * @param noOfUsers Number of mock users to be created.
     * @return Generic success/failure response.
     */
    GenericResponse createUserAndSaveToDb(Integer noOfUsers);

    UsersResponse fetchUsers(String userId);
}
