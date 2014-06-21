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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "messages", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class Message implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    @Column
    @Type(type = "text")
    private String text;

    @Column(name = "date_writing")
    @DateTimeFormat(pattern = "HH:mm dd.MM.yyyy")
    private Date dateWriting;

    @Column(nullable = false, columnDefinition = "TINYINT(4) default '0'")
    private boolean review;

    @Column(name = "sender", nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dialog_id")
    private Dialog dialog;

    public Message() {
        // TODO Auto-generated constructor stub
    }

    public Message(String message, User sender) {
        setText(message);
        setDateWriting(new Date());
        setSender(sender);
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
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
     * @return the dateWriting
     */
    public Date getDateWriting() {
        return dateWriting;
    }

    /**
     * @param dateWriting
     *            the dateWriting to set
     */
    public void setDateWriting(Date dateWriting) {
        this.dateWriting = dateWriting;
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

    /**
     * @return the dialog
     */
    public Dialog getDialog() {
        return dialog;
    }

    /**
     * @param dialog
     *            the dialog to set
     */
    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    /**
     * @return the sender
     */
    public User getSender() {
        return sender;
    }

    /**
     * @param sender
     *            the sender to set
     */
    public void setSender(User sender) {
        this.sender = sender;
    }

}
