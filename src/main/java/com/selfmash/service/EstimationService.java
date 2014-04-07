package com.selfmash.service;

import java.util.List;

import com.selfmash.model.Estimation;

public interface EstimationService {

	public void addEstimation(Estimation estimation);

	public List<Estimation> getEstimationsByPhotoId(long id);

	public void removeEstimationsByPhotoId(long id);

}
