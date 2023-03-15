package com.spotdraft.swapi.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import com.spotdraft.swapi.pojo.PlanetResponse;
import lombok.Data;

import java.util.List;

/**
 * PLanets API response POJO class.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PlanetsResponse extends GenericResponse {

    @SerializedName("planets")
    private List<PlanetResponse> planets;

}
