package com.selfmash.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.selfmash.dao.RequestDAO;

@Repository
public class RequestDAOImpl implements RequestDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Logger logger = Logger.getLogger(getClass());

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Object[]> firstRequest(Date startDate, Date endDate) {
		try {
			Query query = getCurrentSession()
					.createSQLQuery(
							"Select name,lastname,date_reg,confim_reg FROM users where DATE_REG between :startDate AND :endDate AND CONFIM_REG = false")
					.setParameter("startDate", startDate)
					.setParameter("endDate", endDate);
			return query.list();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		return null;
	}

	@Override
	public List<?> secondRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> thirdRequest(String country, String city) {
		try {
			Query query = getCurrentSession()
					.createSQLQuery(
							"Select name,lastname,country,city FROM users where country = :country AND city = :city")
					.setParameter("country", country)
					.setParameter("city", city);
			return query.list();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		return null;
	}

	@Override
	public List<?> fourthRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> fifthRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> sixthRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List seventhRequest() {
		// TODO Auto-generated method stub
		return null;
	}

}
