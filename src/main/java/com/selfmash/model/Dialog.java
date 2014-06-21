package com.selfmash.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dialogs", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class Dialog implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = { javax.persistence.CascadeType.ALL })
    @JoinColumn(name = "dialog_id")
    private List<Message> dialog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user1_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user2_id")
    private User user2;

    public Dialog() {
        // TODO Auto-generated constructor stub
    }

    public Dialog(User user1, User user2) {
        this.user = user1;
        this.user2 = user2;
    }

    public Dialog addMessage(Message message) {
        dialog.add(message);
        return this;
    }

    /**
     * @return the dialog
     */
    public List<Message> getDialog() {
        return dialog;
    }

    /**
     * @param dialog
     *            the dialog to set
     */
    public void setDialog(List<Message> dialog) {
        this.dialog = dialog;
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

}
