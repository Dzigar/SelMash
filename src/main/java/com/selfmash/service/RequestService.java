package com.selfmash.service;

import java.util.Date;
import java.util.List;

public interface RequestService {

	public List<Object[]> firstRequest(Date startDate, Date endDate);

	public List secondRequest();

	public List<Object[]> thirdRequest(String coutry, String city);

	public List fourthRequest();

	public List fifthRequest();

	public List sixthRequest();

	public List seventhRequest();
}
