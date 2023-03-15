package com.spotdraft.swapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Main class.
 */
@SpringBootApplication
@EnableMongoRepositories
public class SwapiDraftApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwapiDraftApplication.class, args);
    }

}
