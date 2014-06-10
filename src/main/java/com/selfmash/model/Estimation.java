package com.selfmash.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ESTIMATION")
public class Estimation implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 2564183876125611370L;

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private float estimation;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { javax.persistence.CascadeType.ALL })
    @JoinTable(name = "user_estimation", joinColumns = { @JoinColumn(name = "estimation_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") })
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { javax.persistence.CascadeType.ALL })
    @JoinTable(name = "photo_estimation", joinColumns = { @JoinColumn(name = "estimation_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "photo_id", referencedColumnName = "id") })
    private Photo photo;

    @OneToOne(fetch = FetchType.EAGER, cascade = { javax.persistence.CascadeType.ALL })
    @JoinTable(name = "post_estimation", joinColumns = { @JoinColumn(name = "estimation_id", nullable = true) }, inverseJoinColumns = { @JoinColumn(name = "post_id", nullable = true) })
    private Post post;

    @Column
    @DateTimeFormat(pattern = "HH:mm dd.MM.yyyy")
    private Date appreciateDate;

    public Estimation() {
        // TODO Auto-generated constructor stub
    }

    public Estimation(float estimation, Photo photo, User user) {
        setEstimation(estimation);
        setPhoto(photo);
        setUser(user);
        setAppreciateDate(new Date());
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
     * @return the appreciateDate
     */
    public Date getAppreciateDate() {
        return appreciateDate;
    }

    /**
     * @param appreciateDate
     *            the appreciateDate to set
     */
    public void setAppreciateDate(Date appreciateDate) {
        this.appreciateDate = appreciateDate;
    }

    /**
     * @return the post
     */
    public Post getPost() {
        return post;
    }

    /**
     * @param post
     *            the post to set
     */
    public void setPost(Post post) {
        this.post = post;
    }

}
