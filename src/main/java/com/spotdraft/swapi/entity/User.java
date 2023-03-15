package com.spotdraft.swapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

/**
 * User database document class.
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Field
    @SerializedName("name")
    private String name;

    @Field
    @SerializedName("created")
    private Long created;

    @Field
    @SerializedName("edited")
    private Long edited;

    @Field("favourite_films")
    @JsonProperty("favourite_films")
    @SerializedName("favourite_films")
    private Set<String> favouriteFilms;

    @Field("favourite_planets")
    @JsonProperty("favourite_planets")
    @SerializedName("favourite_planets")
    private Set<String> favouritePlanets;

}
