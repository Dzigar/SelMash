package com.selfmash.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.dao.PhotoDAO;
import com.selfmash.model.Photo;
import com.selfmash.model.User;

@Service("photoServiceImpl")
@Transactional
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private PhotoDAO photoDAO;

	private Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public void addphoto(Photo selfShot) {
		photoDAO.addphoto(selfShot);
		logger.info("Upload new photo:" + selfShot.getTitle());
	}

	@Override
	public List<Photo> getUserPhotos(User user) {
		return photoDAO.getUserPhotos(user);
	}

	@Override
	public Photo getPhotoById(long id) {
		return photoDAO.getPhotoById(id);
	}

	@Override
	public void updatePhoto(Photo photo) {
		photoDAO.updatePhoto(photo);
	}

	@Override
	public Photo getAccoutPhoto(User user) {
		return photoDAO.getAccoutPhoto(user);
	}

	@Override
	public void deletePhoto(long id) {
		photoDAO.deletePhoto(id);
	}

	@Override
	public long getLastId() {
		return photoDAO.getLastId();
	}

}
