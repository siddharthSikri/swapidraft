package com.spotdraft.swapi.service.impl;

import com.spotdraft.swapi.constants.Constants;
import com.spotdraft.swapi.entity.User;
import com.spotdraft.swapi.pojo.response.GenericResponse;
import com.spotdraft.swapi.pojo.response.UsersResponse;
import com.spotdraft.swapi.repository.FilmsRepo;
import com.spotdraft.swapi.repository.UsersRepo;
import com.spotdraft.swapi.service.PlanetsService;
import com.spotdraft.swapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Users service implementation class.
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    FilmsRepo filmsRepo;

    @Autowired
    PlanetsService planetsService;

    /**
     * Create mock user and save to users collection.
     *
     * @param noOfUsers Number of mock users to be created.
     * @return Generic success/failure response.
     */
    @Override
    public GenericResponse createUserAndSaveToDb(Integer noOfUsers) {
        if (Objects.isNull(noOfUsers)) {
            noOfUsers = 3;
        }
        for (User user : generateUser(noOfUsers)) {
            usersRepo.save(user);
        }
        return new GenericResponse(true, "Users successfully created!", null);
    }

    /**
     * Fetch all users or specific user by id.
     *
     * @param userId User ID.
     * @return List of users.
     */
    @Override
    public UsersResponse fetchUsers(String userId) {
        UsersResponse usersResponse = new UsersResponse();
        try {
            if (StringUtils.hasLength(userId)) {
                Optional<User> userOptional = usersRepo.findById(userId);
                if (userOptional.isPresent()) {
                    usersResponse.setUsers(Collections.singletonList(userOptional.get()));
                    usersResponse.setCount(1);
                } else {
                    usersResponse.setSuccess(true);
                    usersResponse.setMessage(Constants.NO_USER_FOUND);
                    return usersResponse;
                }
            } else {
                List<User> usersList = usersRepo.findAll();
                usersResponse.setUsers(usersList);
                usersResponse.setCount(usersList.size());
            }
            usersResponse.setMessage(Constants.USERS_FETCHED);
            usersResponse.setSuccess(true);
            return usersResponse;
        } catch (Exception e) {
            usersResponse.setMessage(Constants.EXCEPTION_OCCURRED + e.getMessage());
            usersResponse.setSuccess(false);
            return usersResponse;
        }
    }

    /**
     * Create mock user.
     *
     * @param noOfUsers Number of mock users to be created.
     * @return List of users.
     */
    private List<User> generateUser(Integer noOfUsers) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < noOfUsers; i++) {
            Long currentTime = System.currentTimeMillis();
            User user = User.builder()
                    .name("User " + new Random().nextInt(1000))
                    .created(currentTime)
                    .edited(currentTime)
                    .build();
            userList.add(user);
        }
        return userList;
    }
}
