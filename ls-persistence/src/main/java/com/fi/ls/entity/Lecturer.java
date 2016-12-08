package com.fi.ls.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dozer.Mapping;

/**
 *
 * @author Lukas Daubner (410034)
 */
@Entity
@Table(name = "lecturer")
@NamedQueries( {
    @NamedQuery(name = "Lecturer.findAll", query = "SELECT l FROM Lecturer l"),
    @NamedQuery(name = "Lecturer.findAllLecturerLanguages", query = "SELECT lan FROM Language lan WHERE lan.lecturer.id = :lID")
} )
public class Lecturer extends LSUser {

	@NotNull
	@Column(unique = true)
	private String nickname;

	@NotNull
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	private String surname;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = Language.class, mappedBy = "lecturer")
	@Column(name = "list_of_languages")
	@Mapping("listOfLanguages")
	private List<Language> listOfLanguages = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@Column(name = "list_of_lectures")
	@Mapping("listOfLectures")
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

	public void deleteLecture(Lecture lecture) {
		this.listOfLectures.remove(lecture);
	}

	public void deleteLectures(List<Lecture> lectures) {
		lectures.forEach(lect -> listOfLectures.remove(lect));
	}

	public List<Language> getListOfLanguages() {
		return Collections.unmodifiableList(listOfLanguages);
	}
        
        public void setListOfLanguages(List<Language> listOfLanguages) {
            this.listOfLanguages = listOfLanguages;
	}

	public List<Lecture> getListOfLectures() {
		return Collections.unmodifiableList(listOfLectures);
	}

        public void setListOfLectures(List<Lecture> listOfLectures) {
            this.listOfLectures = listOfLectures;
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
			if (other.getNickname() != null)
				return false;
		} else {
			if (!this.nickname.equals(other.getNickname()))
				return false;
		}
		if (this.firstName == null) {
			if (other.getFirstName() != null)
				return false;
		} else {
			if (!this.firstName.equals(other.getFirstName()))
				return false;
		}
		if (this.surname == null) {
			if (other.getSurname() != null)
				return false;
		} else {
			if (!this.surname.equals(other.getSurname()))
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return (nickname == null ? 0 : nickname.hashCode());
	}
}
