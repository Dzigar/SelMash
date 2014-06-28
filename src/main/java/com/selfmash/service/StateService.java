package com.selfmash.service;

import java.util.List;

import com.selfmash.model.City;
import com.selfmash.model.State;

public interface StateService {

    List<State> getAllStates();

    List<String> getCitiesByStateId(long stateId);

    List<City> getCitiesByState(long stateId);

    City getCityByName(String name);
}
