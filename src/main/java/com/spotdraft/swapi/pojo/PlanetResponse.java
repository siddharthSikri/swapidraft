package com.spotdraft.swapi.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.spotdraft.swapi.pojo.response.GenericResponse;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PlanetResponse extends GenericResponse {

    @Field
    @SerializedName("name")
    private String name;

    @Field("custom_name")
    @JsonProperty("custom_name")
    @SerializedName("custom_name")
    private String customName;

    @Field
    @SerializedName("created")
    private String created;

    @Field
    @SerializedName("edited")
    private String edited;

    @Field
    @SerializedName("url")
    private String url;

    @Field("is_favourite")
    @JsonProperty("is_favourite")
    @SerializedName("is_favourite")
    private Boolean isFavourite = false;

    @Field("favourite_users")
    @JsonProperty("favourite_users")
    @SerializedName("favourite_users")
    private Set<String> favouriteUsers;

}
