package com.selfmash.dao;

import java.util.List;

import com.selfmash.model.Photo;
import com.selfmash.model.User;

public interface PhotoDAO {

	public void addphoto(Photo photo);

	public List<Photo> getUserPhotos(User user);

	public Photo getPhotoById(long id);

	public void updatePhoto(Photo photo);

	public Photo getAccoutPhoto(User user);

	public void deletePhoto(long id);

	public long getLastId();

}
