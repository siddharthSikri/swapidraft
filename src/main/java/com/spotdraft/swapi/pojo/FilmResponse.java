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
public class FilmResponse extends GenericResponse {

    @Field
    @SerializedName("title")
    private String title;

    @Field("custom_title")
    @JsonProperty("custom_title")
    @SerializedName("custom_title")
    private String customTitle;

    @Field("release_date")
    @JsonProperty("release_date")
    @SerializedName("release_date")
    private String releaseDate;

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
