package com.selfmash.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue
	@Column
	private long id;

	@Column
	private String role;

	@OneToMany
	@Cascade({ CascadeType.SAVE_UPDATE })
	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") })
	private Set<User> userRoles;

	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the userRoles
	 */
	public Set<User> getUserRoles() {
		return userRoles;
	}

	/**
	 * @param userRoles
	 *            the userRoles to set
	 */
	public void setUserRoles(Set<User> userRoles) {
		this.userRoles = userRoles;
	}

}