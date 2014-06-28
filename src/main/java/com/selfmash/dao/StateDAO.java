package com.selfmash.dao;

import java.util.List;

import com.selfmash.model.City;
import com.selfmash.model.State;

public interface StateDAO {

    List<State> getAllStates();

    List<String> getCitiesByStateId(long stateId);

    List<City> getCitiesByState(long stateId);

    City getCityByName(String name);
}
