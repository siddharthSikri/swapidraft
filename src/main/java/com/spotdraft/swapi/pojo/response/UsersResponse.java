package com.spotdraft.swapi.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import com.spotdraft.swapi.entity.User;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UsersResponse extends GenericResponse {

    @SerializedName("users")
    private List<User> users;

}
