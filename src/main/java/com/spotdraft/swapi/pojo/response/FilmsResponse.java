package com.spotdraft.swapi.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import com.spotdraft.swapi.pojo.FilmResponse;
import lombok.Data;

import java.util.List;

/**
 * Films API response POJO class.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FilmsResponse extends GenericResponse {

    @SerializedName("films")
    private List<FilmResponse> films;

}
