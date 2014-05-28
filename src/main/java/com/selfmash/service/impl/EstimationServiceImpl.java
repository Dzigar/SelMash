package com.selfmash.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.EstimationDAO;
import com.selfmash.model.Estimation;
import com.selfmash.service.EstimationService;

@Service("estimationServiceImpl")
@Transactional
public class EstimationServiceImpl implements EstimationService {

	@Autowired
	private EstimationDAO estimationDAO;

	@Override
	public void addEstimation(Estimation estimation) {
		estimationDAO.addEstimation(estimation);
	}

	@Override
	public void removeEstimationsByPhotoId(long id) {
		estimationDAO.removeEstimationsByPhotoId(id);
	}

	@Override
	public List<Estimation> getEstimationsByPhotoId(long id) {
		return estimationDAO.getEstimationsByPhotoId(id);
	}

}
