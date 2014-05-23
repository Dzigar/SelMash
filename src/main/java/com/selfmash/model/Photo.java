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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.selfmash.model.base.Model;

@Entity
@Table(name = "photo", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class Photo extends Model implements Serializable {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade({ CascadeType.EVICT })
    @JoinTable(name = "user_photo", joinColumns = { @JoinColumn(name = "photo_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") })
    private User user;

    @Column(name = "account_photo", nullable = false, columnDefinition = "boolean default false")
    private Boolean isAccountPhoto;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_PREFERENCES", joinColumns = { @JoinColumn(name = "photo_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") })
    private Set<User> fans;

    @Column(nullable = true)
    private float averageRating;

    public Photo() {
        // TODO Auto-generated constructor stub
    }

    public Photo(String title, Date dateUpload, User user) {
        this.title = title;
        this.dateUpload = dateUpload;
        this.user = user;
        this.isAccountPhoto = false;
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
