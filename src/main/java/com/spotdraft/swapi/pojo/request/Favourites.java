package com.spotdraft.swapi.pojo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Favourites request POJO class.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Favourites {

    @SerializedName("favourite")
    private Boolean favourite;

    @JsonProperty("custom_planet_name")
    @SerializedName("custom_planet_name")
    private String customPlanetName;

    @JsonProperty("custom_film_title")
    @SerializedName("custom_film_title")
    private String customFilmTitle;

}
