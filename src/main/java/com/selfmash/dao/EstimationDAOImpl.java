package com.selfmash.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.selfmash.model.Estimation;

@Repository
public class EstimationDAOImpl implements EstimationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Logger logger = Logger.getLogger(getClass().getName());

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addEstimation(Estimation estimation) {
		try {
			getCurrentSession().save(estimation);
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage());
		}
	}

	@Override
	public void removeEstimationsByPhotoId(long id) {
		try {
			List<Estimation> estimations = getEstimationsByPhotoId(id);
//			for (Estimation estimation : estimations) {
//				estimation.getUser().getEstimations().remove(estimation);
//				getCurrentSession().delete(estimation);
//			}
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Estimation> getEstimationsByPhotoId(long id) {
		try {

			return getCurrentSession()
					.createQuery(
							"from Estimation as u where u.photoId = :PHOTO_ID")
					.setParameter("PHOTO_ID", id).list();

		} catch (Exception e) {
			logger.info(e.getLocalizedMessage());
		}
		return null;
	}
}
