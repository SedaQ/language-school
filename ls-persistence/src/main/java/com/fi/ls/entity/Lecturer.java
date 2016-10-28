package com.fi.ls.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lukas Daubner (410034)
 */
@Entity
@NamedQuery(name = "Lecturer.findAll", query = "SELECT l FROM Lecturer l")
public class Lecturer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotNull
    @Column(unique = true)
	private String nickname;
        
	@NotNull
	private String firstName;

	@NotNull
	private String surname;

	@OneToMany(targetEntity = Language.class, mappedBy = "lecturer")
	private List<Language> listOfLanguages = new ArrayList<>();

	@ManyToMany
	private List<Lecture> listOfLectures = new ArrayList<>();

	public Long getId() {
		return id;
	}

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
        
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Language> getListOfLanguages() {
		return Collections.unmodifiableList(listOfLanguages);
	}

	public List<Lecture> getListOfLectures() {
		return Collections.unmodifiableList(listOfLectures);
	}

	public void addLanguage(Language lan) {
		listOfLanguages.add(lan);
		lan.setLecturer(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Lecturer))
			return false;
		Lecturer other = (Lecturer) obj;
                if (this.nickname == null) {
			if (other.nickname != null)
				return false;
		} else {
			if (!this.nickname.equals(other.nickname))
				return false;
		}
		if (this.firstName == null) {
			if (other.firstName != null)
				return false;
		} else {
			if (!this.firstName.equals(other.firstName))
				return false;
		}
		if (this.surname == null) {
			if (other.surname != null)
				return false;
		} else {
			if (!this.surname.equals(other.surname))
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return (nickname == null ? 0 : nickname.hashCode());
	}
}
