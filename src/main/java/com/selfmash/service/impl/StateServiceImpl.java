package com.selfmash.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.StateDAO;
import com.selfmash.model.City;
import com.selfmash.model.State;
import com.selfmash.service.StateService;

@Service("StateService")
@Transactional
public class StateServiceImpl implements StateService {

    @Autowired
    private StateDAO stateDAO;

    @Override
    public List<State> getAllStates() {
        return stateDAO.getAllStates();
    }

    @Override
    public List<String> getCitiesByStateId(long stateId) {
        return stateDAO.getCitiesByStateId(stateId);
    }

    @Override
    public City getCityByName(String name) {
        return stateDAO.getCityByName(name);
    }

    @Override
    public List<City> getCitiesByState(long stateId) {
               return stateDAO.getCitiesByState(stateId);
    }

}
