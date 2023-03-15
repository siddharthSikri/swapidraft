package com.spotdraft.swapi.pojo.response.swapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Generic response POJO from SWAPI.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SwapiGenericResponse {

    @Field
    @SerializedName("count")
    private Integer count;

    @Field
    @SerializedName("next")
    private String next;

    @Field
    @SerializedName("previous")
    private String previous;

}
