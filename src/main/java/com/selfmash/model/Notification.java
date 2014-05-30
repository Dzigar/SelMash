package com.selfmash.model;

import java.io.Serializable;

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

import com.selfmash.beans.enums.NotificationBody;
import com.selfmash.model.base.Model;

@Entity
@Table(name = "notification", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class Notification extends Model implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @OneToOne
    @JoinTable(name = "sender_notification", joinColumns = { @JoinColumn(name = "notification_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "sender_id", referencedColumnName = "id") })
    private User sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "notification_user", joinColumns = { @JoinColumn(name = "notification_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) })
    private User receiver;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationBody notificationMessage;

    @Column(nullable = false, columnDefinition = "TINYINT(4) default '0'")
    private boolean review;

    public Notification() {
        // TODO Auto-generated constructor stub
    }

    public Notification(User sender, User receiver,
            NotificationBody notificationBody) {
        this.sender = sender;
        this.receiver = receiver;
        this.notificationMessage = notificationBody;
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
    public User getSender() {
        return sender;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setSender(User sender) {
        this.sender = sender;
    }

    /**
     * @return the notificationMessage
     */
    public NotificationBody getNotificationMessage() {
        return notificationMessage;
    }

    /**
     * @param notificationMessage
     *            the notificationMessage to set
     */
    public void setNotificationMessage(NotificationBody notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    /**
     * @return the receiver
     */
    public User getReceiver() {
        return receiver;
    }

    /**
     * @param receiver
     *            the receiver to set
     */
    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    /**
     * @return the review
     */
    public boolean isReview() {
        return review;
    }

    /**
     * @param review
     *            the review to set
     */
    public void setReview(boolean review) {
        this.review = review;
    }
}
