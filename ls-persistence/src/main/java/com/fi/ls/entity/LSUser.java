package com.fi.ls.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "lsuser")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "LSUser.findAll", query = "SELECT u FROM LSUser u")
public class LSUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_user")
	protected Long id;

	@NotNull
	@Pattern(regexp = ".+@.+\\....?")
	@Column(unique = true)
	private String email;

	@NotNull
	private String passwordHash;

	public LSUser() {
	}

	public Long getId() {
		return id;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
