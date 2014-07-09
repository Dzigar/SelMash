package com.selfmash.model;

import java.io.Serializable;

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

@Entity
@Table(name = "meets", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class Meet implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_from")
    private User userFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_to")
    private User userTo;

    @Column(name = "accept")
    private boolean accept;

    public Meet() {
        // TODO Auto-generated constructor stub
    }

    public Meet(User userFrom, User userTo) {
        setUserFrom(userFrom);
        setUserTo(userTo);
        setAccept(false);
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
     * @return the userFrom
     */
    public User getUserFrom() {
        return userFrom;
    }

    /**
     * @param userFrom
     *            the userFrom to set
     */
    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    /**
     * @return the userTo
     */
    public User getUserTo() {
        return userTo;
    }

    /**
     * @param userTo
     *            the userTo to set
     */
    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    /**
     * @return the accept
     */
    public boolean isAccept() {
        return accept;
    }

    /**
     * @param accept
     *            the accept to set
     */
    public void setAccept(boolean accept) {
        this.accept = accept;
    }

}
