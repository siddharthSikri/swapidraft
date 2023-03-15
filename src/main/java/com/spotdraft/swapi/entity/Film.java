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
 * Film database document class.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "films")
public class Film {

    @Id
    private String id;

    @Field
    @SerializedName("title")
    private String title;

    @Field("custom_title")
    @JsonProperty("custom_title")
    @SerializedName("custom_title")
    private String customTitle;

    @Field("episode_id")
    @JsonProperty("episode_id")
    @SerializedName("episode_id")
    private String episodeId;

    @Field("opening_crawl")
    @JsonProperty("opening_crawl")
    @SerializedName("opening_crawl")
    private String openingCrawl;

    @Field
    @SerializedName("director")
    private String director;

    @Field
    @SerializedName("producer")
    private String producer;

    @Field("release_date")
    @JsonProperty("release_date")
    @SerializedName("release_date")
    private String releaseDate;

    @Field
    @SerializedName("characters")
    private List<String> characters;

    @Field
    @SerializedName("planets")
    private List<String> planets;

    @Field
    @SerializedName("starships")
    private List<String> starships;

    @Field
    @SerializedName("vehicles")
    private List<String> vehicles;

    @Field
    @SerializedName("species")
    private List<String> species;

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
