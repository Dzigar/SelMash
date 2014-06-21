package com.selfmash.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

import com.selfmash.beans.enums.ActionBody;

@Entity
@Table(name = "post", uniqueConstraints = @UniqueConstraint(columnNames = { "id_post" }))
public class Post implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { javax.persistence.CascadeType.ALL })
    @JoinTable(name = "post_user", joinColumns = { @JoinColumn(name = "post_id", nullable = true, updatable = true) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = true, updatable = true) })
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActionBody action;

    @OneToOne(fetch = FetchType.EAGER, cascade = { javax.persistence.CascadeType.ALL })
    @JoinTable(name = "post_follower", joinColumns = { @JoinColumn(name = "post_id", nullable = true, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "follower_id", nullable = true, updatable = false) })
    private User follower;

    @OneToOne(fetch = FetchType.EAGER, cascade = { javax.persistence.CascadeType.ALL })
    @JoinTable(name = "post_photo", joinColumns = { @JoinColumn(name = "post_id", nullable = true, updatable = true) }, inverseJoinColumns = { @JoinColumn(name = "photo_id", nullable = true, updatable = true) })
    private Photo photo;

    @OneToOne(fetch = FetchType.EAGER, cascade = { javax.persistence.CascadeType.ALL })
    @JoinTable(name = "post_estimation", joinColumns = { @JoinColumn(name = "post_id", nullable = true, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "estimation_id", nullable = true, updatable = false) })
    private Estimation estimation;

    @Column
    @DateTimeFormat(pattern = "HH:mm dd.MM.yyyy")
    private Date dateCreate;

    /**
     * Default constructor
     */
    public Post() {
        // TODO Auto-generated constructor stub
    }

    /**
     * This constructor called when user subscribe to another user.
     * 
     * @param user
     *            - user subscriber
     * @param follower
     *            - one to sign up.
     */
    public Post(User user, User follower) {
        setUser(user);
        setFollower(follower);
        setAction(ActionBody.SUBSCRIBE);
        setDateCreate(new Date());
    }

    /**
     * This constructor called when user uploaded photo.
     * 
     * @param user
     * @param photo
     */
    public Post(User user, Photo photo) {
        setUser(user);
        setPhoto(photo);
        setAction(ActionBody.UPLOAD_PHOTO);
        setDateCreate(new Date());
    }

    /**
     * This constructor called when user appreciated photo.
     * 
     * @param user
     * @param estimation
     *            - estimation for some photo.
     */
    public Post(User user, Estimation estimation) {
        setUser(user);
        setPhoto(estimation.getPhoto());
        setAction(ActionBody.APPRECIATE_PHOTO);
        setDateCreate(new Date());
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
     * @return the photo
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     * @param photo
     *            the photo to set
     */
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    /**
     * @return the action
     */
    public ActionBody getAction() {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(ActionBody action) {
        this.action = action;
    }

    /**
     * @return the follower
     */
    public User getFollower() {
        return follower;
    }

    /**
     * @param follower
     *            the follower to set
     */
    public void setFollower(User follower) {
        this.follower = follower;
    }

    /**
     * @return the dateCreate
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * @param dateCreate
     *            the dateCreate to set
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * @return the estimation
     */
    public Estimation getEstimation() {
        return estimation;
    }

    /**
     * @param estimation
     *            the estimation to set
     */
    public void setEstimation(Estimation estimation) {
        this.estimation = estimation;
    }

}
