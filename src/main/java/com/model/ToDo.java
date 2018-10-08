/**
 * 
 */
package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author virens
 *
 */
@Entity
@Table(name = "TODO")
public class ToDo {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "completed", nullable = false)
	private boolean completed;

	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "username")
	private User user;

	public ToDo() {

	}

	public ToDo(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "name: " + getName();
	}

}
