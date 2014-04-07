package com.selfmash.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ESTIMATION")
public class Estimation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2564183876125611370L;

	@Id
	@Column(name = "ID", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "ESTIMATION")
	private float estimation;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "USERS_ESTIMATES", joinColumns = { @JoinColumn(name = "ESTIMATION_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") })
	private User user;

	@Column(name = "PHOTO_ID")
	private long photoId;

	public Estimation() {
		// TODO Auto-generated constructor stub
	}

	public Estimation(float estimation, long photoId, User user) {
		this.estimation = estimation;
		this.photoId = photoId;
		this.user = user;
	}

	/**
	 * @return the estimation
	 */
	public float getEstimation() {
		return estimation;
	}

	/**
	 * @param estimation
	 *            the estimation to set
	 */
	public void setEstimation(float estimation) {
		this.estimation = estimation;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the users
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the photoId
	 */
	public long getPhotoId() {
		return photoId;
	}

	/**
	 * @param photoId
	 *            the photoId to set
	 */
	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

}
