package com.spotdraft.swapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.spotdraft.swapi.constants.Constants;
import com.spotdraft.swapi.entity.Planet;
import com.spotdraft.swapi.helper.Helper;
import com.spotdraft.swapi.repository.PlanetsRepo;
import com.spotdraft.swapi.service.PlanetsService;
import com.spotdraft.swapi.utilities.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.spotdraft.swapi.pojo.PlanetResponse;
import com.spotdraft.swapi.pojo.response.GenericResponse;
import com.spotdraft.swapi.pojo.response.PlanetsResponse;
import com.spotdraft.swapi.pojo.response.swapi.SwapiPlanetsResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Planets service implementation class.
 */
@Service
public class PlanetsServiceImpl implements PlanetsService {

    @Autowired
    WebUtils webUtils;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Helper helper;

    @Autowired
    PlanetsRepo planetsRepo;

    @Autowired
    Gson gson;

    /**
     * Sync planets from SWAPI and save to planets collection.
     *
     * @param url SWAPI URL.
     * @return Generic success/failure response.
     */
    @Override
    public GenericResponse syncPlanetsAndSaveToDb(String url) {
        try {
            String response = webUtils.get(url);
            SwapiPlanetsResponse planets = objectMapper.readValue(response, SwapiPlanetsResponse.class);
            helper.saveEntitiesToDb(null, planets);
            if (null == planets.getNext()) {
                return new GenericResponse(true, Constants.PLANETS_SYNCED, null);
            } else {
                syncPlanetsAndSaveToDb(planets.getNext());
            }
            return new GenericResponse(true, Constants.PLANETS_SYNCED, null);
        } catch (Exception e) {
            return new GenericResponse(false, Constants.EXCEPTION_OCCURRED + e.getMessage(), null);
        }
    }

    /**
     * Fetch all planets or specific planet based on planet name / custom name.
     *
     * @param planetName Planet name/custom name.
     * @return List of planets.
     */
    @Override
    public PlanetsResponse fetchPlanets(String planetName) {
        PlanetsResponse planetsResponse = new PlanetsResponse();
        try {
            List<PlanetResponse> planetResponseList = new ArrayList<>();
            List<Planet> planetList;
            if (StringUtils.hasLength(planetName)) {
                planetList = fetchPlanetsByTitleOrCustomTitle(planetName);
            } else {
                planetList = planetsRepo.findAll();
            }
            for (Planet planet : planetList) {
                PlanetResponse planetResponse = mapPlanetResponse(planet);
                if (StringUtils.hasLength(planet.getCustomName())) {
                    planetResponse.setCustomName(planet.getCustomName());
                }
                planetResponseList.add(planetResponse);
            }
            planetsResponse.setPlanets(planetResponseList);
            planetsResponse.setCount(planetResponseList.size());
            planetsResponse.setSuccess(true);
            planetsResponse.setMessage(Constants.PLANETS_FETCHED);
            return planetsResponse;
        } catch (Exception e) {
            planetsResponse.setSuccess(false);
            planetsResponse.setMessage(Constants.EXCEPTION_OCCURRED + e.getMessage());
            return planetsResponse;
        }
    }

    /**
     * Map SWAPI planet response to required API response.
     *
     * @param planet SWAPI Planet details.
     * @return Planet response wrt API.
     */
    private PlanetResponse mapPlanetResponse(Planet planet) {
        PlanetResponse planetResponse = new PlanetResponse();
        try {
            planetResponse = objectMapper.readValue(gson.toJson(planet), PlanetResponse.class);
            return planetResponse;
        } catch (Exception e) {
            planetResponse.setSuccess(false);
            planetResponse.setMessage(Constants.EXCEPTION_OCCURRED + e.getMessage());
            return planetResponse;
        }
    }

    /**
     * Fetch planet by name or custom name.
     *
     * @param planetName Planet name.
     * @return List of planets.
     */
    private List<Planet> fetchPlanetsByTitleOrCustomTitle(String planetName) {
        List<Planet> planetList = planetsRepo.findAllByCustomName(planetName);
        if (CollectionUtils.isEmpty(planetList)) {
            planetList = planetsRepo.findAllByName(planetName);
        }
        return planetList;
    }
}
