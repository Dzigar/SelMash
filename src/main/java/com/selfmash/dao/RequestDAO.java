package com.selfmash.dao;

import java.util.Date;
import java.util.List;

public interface RequestDAO {

	public List<Object[]> firstRequest(Date startDate, Date endDate);

	public List<?> secondRequest();

	public List<?> thirdRequest();

	public List<?> fourthRequest();

	public List<?> fifthRequest();

	public List<?> sixthRequest();

	public List<?> seventhRequest();
}
