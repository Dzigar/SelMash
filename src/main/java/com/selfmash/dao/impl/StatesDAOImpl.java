package com.selfmash.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.selfmash.dao.StateDAO;
import com.selfmash.model.City;
import com.selfmash.model.State;

@Repository
public class StatesDAOImpl implements StateDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<State> getAllStates() {
        return sessionFactory.getCurrentSession().createQuery("from State")
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getCitiesByStateId(long stateId) {
        return sessionFactory
                .getCurrentSession()
                .createSQLQuery(
                        "select name from city c where c.state_id = :stateId")
                .setParameter("stateId", stateId).list();
    }

    @Override
    public City getCityByName(String name) {
        return (City) sessionFactory.getCurrentSession()
                .createQuery("from City c where c.name = :name")
                .setParameter("name", name).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<City> getCitiesByState(long stateId) {
        List<City> cities = null;
        try {
            cities = sessionFactory
                    .getCurrentSession()
                    .createSQLQuery(
                            "Select * from city c where c.state_id = :stateId")
                    .addEntity(City.class).setParameter("stateId", stateId)
                    .list();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return cities;
    }
}
