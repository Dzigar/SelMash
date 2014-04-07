package com.selfmash.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.RequestDAO;

@Service("requestServiceImpl")
@Transactional
public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestDAO requestDAO;

	@Override
	public List<Object[]> firstRequest(Date startDate, Date endDate) {
		return requestDAO.firstRequest(startDate, endDate);
	}

	@Override
	public List secondRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> thirdRequest(String country, String city) {
		return requestDAO.thirdRequest(country, city);
	}

	@Override
	public List fourthRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List fifthRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List sixthRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List seventhRequest() {
		// TODO Auto-generated method stub
		return null;
	}

}
