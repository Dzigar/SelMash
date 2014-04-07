package com.selfmash.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.model.Photo;
import com.selfmash.model.User;
import com.selfmash.service.EstimationService;
import com.selfmash.service.UserService;

@Repository
public class PhotoDAOImpl implements PhotoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Resource(name = "estimationServiceImpl")
	private EstimationService estimationService;

	private Logger logger = Logger.getLogger(getClass().getName());

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	@Transactional
	public void addphoto(Photo selfShot) {
		getCurrentSession().save(selfShot);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> getUserPhotos(User user) {
		return getCurrentSession()
				.createQuery("from Photo as u where u.user = :user")
				.setParameter("user", user).list();
	}

	@Override
	public Photo getPhotoById(long id) {
		return (Photo) getCurrentSession()
				.createQuery("from Photo as u where u.id = :id")
				.setParameter("id", id).uniqueResult();
	}

	@Override
	public void updatePhoto(Photo photo) {
		getCurrentSession().update(photo);
	}

	@Override
	public Photo getAccoutPhoto(User user) {
		return (Photo) getCurrentSession()
				.createQuery(
						"from Photo as u where u.user = :user and u.isAccountPhoto = :isAccountPhoto")
				.setParameter("user", user)
				.setParameter("isAccountPhoto", true).uniqueResult();
	}

	@Override
	public void deletePhoto(long id) {
		try {
			getCurrentSession().beginTransaction();
			estimationService.removeEstimationsByPhotoId(id);
			getCurrentSession().delete(getPhotoById(id));
			getCurrentSession().getTransaction().commit();
			if (getCurrentSession() != null) {
				getCurrentSession().close();
			}
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage());
		}
	}

}
