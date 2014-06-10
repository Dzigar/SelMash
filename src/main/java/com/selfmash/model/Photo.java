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

    @OneToMany(fetch = FetchType.EAGER, cascade = { javax.persistence.CascadeType.ALL })
    @JoinTable(name = "post_photo", joinColumns = { @JoinColumn(name = "photo_id", nullable = true, updatable = true) }, inverseJoinColumns = { @JoinColumn(name = "post_id", nullable = true, updatable = true) })
    private Set<Post> posts;

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

    public Photo addPost(Post post) {
        this.posts.add(post);
        return this;
    }

    /**
     * @return the posts
     */
    public Set<Post> getPosts() {
        return posts;
    }

    /**
     * @param posts
     *            the posts to set
     */
    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

}
