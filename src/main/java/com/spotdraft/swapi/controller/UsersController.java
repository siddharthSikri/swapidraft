package com.spotdraft.swapi.controller;

import com.spotdraft.swapi.pojo.response.GenericResponse;
import com.spotdraft.swapi.pojo.response.UsersResponse;
import com.spotdraft.swapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller class.
 */
@RestController
public class UsersController {

    @Autowired
    UsersService usersService;

    /**
     * Create mock users.
     *
     * @param count Count of mock users to be created.
     * @return Generic success/failure response.
     */
    @PostMapping("/users")
    public GenericResponse createUsersAndSaveToDb(@RequestParam(name = "count", required = false) Integer count) {
        return usersService.createUserAndSaveToDb(count);
    }

    /**
     * Fetch all users or a specific user.
     *
     * @param userId User ID.
     * @return List of users.
     */
    @GetMapping("/users")
    public UsersResponse fetchUsers(
            @RequestParam(name = "user_id", required = false) String userId) {
        return usersService.fetchUsers(userId);
    }
}
