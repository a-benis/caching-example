package com.example.caching_example.authentication.utils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * The type Auth request.
 */
public class AuthRequest implements Serializable {

	/**
	 * The constant serialVersionUID.
	 */
	private static final long serialVersionUID = 5926468583005150707L;

	/**
	 * The Username.
	 */
	@NotBlank
	private String username;

	/**
	 * The Password.
	 */
	@NotBlank
	private String password;

	/**
	 * Gets username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets username.
	 *
	 * @param username the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
