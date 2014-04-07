package com.selfmash.checking;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@FieldEquals(field = "password", equalsTo = "enterpassword")
public class RegistrationUserValidation {

	@NotEmpty(message = "Login must between 4 to 16 characters")
	@Size(min = 4, max = 16)
	@Column(unique = true)
	private String login;

	@NotEmpty(message = "Password must not be blank.")
	private String password;

	@NotEmpty(message = "Enterpassword must not be blank.")
	private String enterpassword;

	@NotEmpty(message = "Name must between 2 to 16 characters.")
	@Size(min = 2, max = 255)
	private String name;

	@NotEmpty(message = "Lastname must between 4 to 16 characters.")
	@Size(min = 2, max = 255)
	private String lastname;

	@Email
	private String email;

	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@NotNull
	@Past
	private Date birthDate;

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the enterpassword
	 */
	public String getEnterpassword() {
		return enterpassword;
	}

	/**
	 * @param enterpassword
	 *            the enterpassword to set
	 */
	public void setEnterpassword(String enterpassword) {
		this.enterpassword = enterpassword;
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

}
