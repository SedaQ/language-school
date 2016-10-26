package com.fi.ls.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Matúš
 */
@Entity
@NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column
	private String name;

	@NotNull
	@Column
	private String surname;

	@ManyToMany
	private List<Lecture> listOfLectures = new ArrayList<>();

	public Student() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setListOfLectures(List<Lecture> listOfLectures) {
		this.listOfLectures = listOfLectures;
	}

	public void addLecture(Lecture lecture) {
		this.listOfLectures.add(lecture);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public List<Lecture> getListOfLectures() {
		return listOfLectures;
	}

	@Override
	public int hashCode() {
		return 31 + ((id == null) ? 0 : id.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}