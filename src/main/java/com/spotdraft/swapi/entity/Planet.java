package com.spotdraft.swapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;

/**
 * Planet database document class.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "planets")
public class Planet {

    @Id
    private String id;

    @Field
    @SerializedName("name")
    private String name;

    @Field("custom_name")
    @JsonProperty("custom_name")
    @SerializedName("custom_name")
    private String customName;

    @Field("rotation_period")
    @JsonProperty("rotation_period")
    @SerializedName("rotation_period")
    private String rotationPeriod;

    @Field("orbital_period")
    @JsonProperty("orbital_period")
    @SerializedName("orbital_period")
    private String orbitalPeriod;

    @Field
    @SerializedName("diameter")
    private String diameter;

    @Field
    @SerializedName("climate")
    private String climate;

    @Field
    @SerializedName("gravity")
    private String gravity;

    @Field
    @SerializedName("terrain")
    private String terrain;

    @Field("surface_water")
    @JsonProperty("surface_water")
    @SerializedName("surface_water")
    private String surfaceWater;

    @Field
    @SerializedName("population")
    private String population;

    @Field
    @SerializedName("residents")
    private List<String> residents;

    @Field
    @SerializedName("films")
    private List<String> films;

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
