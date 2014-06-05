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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "photo", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class Photo implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    @Column(nullable = false)
    private String title;

    @Temporal(TemporalType.DATE)
    @Column
    private Date dateUpload;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { javax.persistence.CascadeType.ALL })
    @JoinTable(name = "user_photo", joinColumns = { @JoinColumn(name = "photo_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") })
    private User user;

    @OneToOne(fetch = FetchType.EAGER, cascade = { javax.persistence.CascadeType.ALL })
    @JoinTable(name = "post_photo", joinColumns = { @JoinColumn(name = "photo_id", nullable = true) }, inverseJoinColumns = { @JoinColumn(name = "post_id", nullable = true) })
    private Post post;

    @Column(nullable = true)
    private float averageRating;

    @OneToMany(fetch = FetchType.EAGER, cascade = { javax.persistence.CascadeType.ALL })
    @JoinTable(name = "photo_estimation", joinColumns = { @JoinColumn(name = "photo_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "estimation_id", referencedColumnName = "id") })
    private Set<Estimation> estimations;

    public Photo() {
        // TODO Auto-generated constructor stub
    }

    public Photo(String title, Date dateUpload, User user) {
        this.title = title;
        this.dateUpload = dateUpload;
        this.user = user;
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
     * @return the name
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setTitle(String title) {
        this.title = title;
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

    /**
     * @return the estimations
     */
    public Set<Estimation> getEstimations() {
        return estimations;
    }

    /**
     * @param estimations
     *            the estimations to set
     */
    public void setEstimations(Set<Estimation> estimations) {
        this.estimations = estimations;
    }

    public Photo addEstimation(Estimation estimation) {
        this.estimations.add(estimation);
        return this;
    }

}
