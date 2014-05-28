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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Past;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import com.selfmash.beans.enums.ActionBody;
import com.selfmash.model.base.Model;

@Entity
@Table(name = "post", uniqueConstraints = @UniqueConstraint(columnNames = { "id_post" }))
public class Post extends Model implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "post_user", joinColumns = { @JoinColumn(name = "post_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) })
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActionBody action;

    @OneToOne(fetch = FetchType.EAGER)
    @Cascade({ CascadeType.ALL })
    @JoinTable(name = "post_friend", joinColumns = { @JoinColumn(name = "post_id", nullable = true, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_friend_id", nullable = true, updatable = false) })
    private User friend;

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade({ CascadeType.ALL })
    @JoinTable(name = "post_photo", joinColumns = { @JoinColumn(name = "post_id", nullable = true, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "photo_id", nullable = true, updatable = false) })
    private Photo photo;

    /**
     * Default constructor
     */
    public Post() {
        // TODO Auto-generated constructor stub
    }

    public Post(User user, User friend, Photo photo, ActionBody actiont) {
        this.user = user;
        this.friend = friend;
        this.photo = photo;
        this.setAction(actiont);
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
     * @return the dateCreate
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * @param dateCreate
     *            the dateCreate to set
     */
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:MM")
    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "date_create")
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
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
     * @return the friend
     */
    public User getFriend() {
        return friend;
    }

    /**
     * @param friend
     *            the friend to set
     */
    public void setFriend(User friend) {
        this.friend = friend;
    }

}
