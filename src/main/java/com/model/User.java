/**
 * 
 */
package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author virens
 *
 */
@Entity
@Table(name = "USERS")
public class User {

	@Id
	@Column(name = "username", unique = true, nullable = false, length = 255)
	private String username;

	@NotBlank
	@Size(max = 255)
	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@NotNull
	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	public User() {
		
	}
	
	public User (String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
