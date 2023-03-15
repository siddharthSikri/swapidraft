package com.spotdraft.swapi.pojo.response.swapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.spotdraft.swapi.entity.Film;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Films API response POJO from SWAPI.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SwapiFilmsResponse extends SwapiGenericResponse {

    @Field
    @JsonProperty("results")
    @SerializedName("results")
    private List<Film> filmResults;

}
