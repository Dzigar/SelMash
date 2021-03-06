package com.selfmash.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import com.selfmash.model.enums.Sex;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {
        "id", "email", "login" }))
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Size(min = 4, max = 16)
    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Size(min = 2, max = 16)
    @Column(nullable = false)
    private String name;

    @Size(min = 2, max = 16)
    @Column(nullable = false)
    private String lastname;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Past
    @Temporal(TemporalType.DATE)
    @Column
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToOne
    private City city;

    @Column(nullable = true, columnDefinition = "float(1) default '0.0'")
    private float rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
    private Role role;

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade({ CascadeType.ALL })
    @JoinTable(name = "user_photo", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "photo_id", referencedColumnName = "id") })
    private Set<Photo> photos;

    @OneToMany(fetch = FetchType.EAGER, cascade = { javax.persistence.CascadeType.ALL })
    @JoinTable(name = "post_user", joinColumns = { @JoinColumn(name = "user_id", nullable = true, updatable = true, referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "post_id", nullable = true, updatable = true) })
    private List<Post> userPosts;

    @OneToMany(fetch = FetchType.LAZY)
    @Cascade({ CascadeType.ALL })
    @JoinTable(name = "notification_user", joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false, referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "notification_id", nullable = false, updatable = false) })
    private List<Notification> notifications;

    @OneToOne(fetch = FetchType.EAGER, cascade = { javax.persistence.CascadeType.ALL })
    @Cascade({ CascadeType.ALL })
    private Photo profilePhoto;

    public User() {
        // TODO Auto-generated constructor stub
    }

    public User(String name, String lastname, Date birthDate, String login,
            String password, String email, Role role, Sex sex) {
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.sex = sex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname
     *            the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate
     *            the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the photos
     */
    public Set<Photo> getPhotos() {
        return photos;
    }

    /**
     * @param photos
     *            the photos to set
     */
    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    /**
     * @return the userPosts
     */
    public List<Post> getUserPosts() {
        return userPosts;
    }

    /**
     * @param userPosts
     *            the userPosts to set
     */
    public void setUserPosts(List<Post> userPosts) {
        this.userPosts = userPosts;
    }

    /**
     * @return the notifications
     */
    public List<Notification> getNotifications() {
        return notifications;
    }

    /**
     * @param notifications
     *            the notifications to set
     */
    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    /**
     * @return the profilePhoto
     */
    public Photo getProfilePhoto() {
        return profilePhoto;
    }

    /**
     * @param profilePhoto
     *            the profilePhoto to set
     */
    public void setProfilePhoto(Photo profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public User addPost(Post post) {
        this.userPosts.add(post);
        return this;
    }

    /**
     * @return the rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * @param rating
     *            the rating to set
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    /**
     * @return the sex
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    /**
     * @return the city
     */
    public City getCity() {
        return city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(City city) {
        this.city = city;
    }
}
