package com.selfmash.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "PHOTO")
public class Photo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true)
	private long id;

	@Column(name = "NAME", nullable = false)
	private String name;
	

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_UPLOAD")
	private Date dateUpload;

	@ManyToOne
	@Cascade({ CascadeType.EVICT })
	@JoinTable(name = "USERS_PHOTOS", joinColumns = { @JoinColumn(name = "PHOTO_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") })
	private User user;
	
	@Column(name = "IS_ACCOUNT_PHOTO", nullable = false, columnDefinition = "boolean default false")
	private Boolean isAccountPhoto;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USERS_PREFERENCES", joinColumns = { @JoinColumn(name = "PHOTO_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") })
	private Set<User> fans;

	@Column(name = "AVAREGE", nullable = true)
	private float averageRating;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the isAccountPhoto
	 */
	public Boolean isAccountPhoto() {
		return isAccountPhoto;
	}

	/**
	 * @param isAccountPhoto
	 *            the isAccountPhoto to set
	 */
	public void setAccountPhoto(Boolean isAccountPhoto) {
		this.isAccountPhoto = isAccountPhoto;
	}

	/**
	 * @return the fans
	 */
	public Set<User> getFans() {
		return fans;
	}

	/**
	 * @param fans
	 *            the fans to set
	 */
	public void setFans(Set<User> fans) {
		this.fans = fans;
	}

	public float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}

	public Date getDateUpload() {
		return dateUpload;
	}

	public void setDateUpload(Date dateUpload) {
		this.dateUpload = dateUpload;
	}

}
