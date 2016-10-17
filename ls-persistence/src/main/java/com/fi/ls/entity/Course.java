package com.fi.ls.entity;

import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fi.ls.enums.ProficiencyLevel;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	@Column
	private Locale language;

	@Enumerated
	private ProficiencyLevel proficiencyLevel;

	// @Column
	// private List<Lecture> listOfLectures;

	public Course() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Course))
			return false;
		Course other = (Course) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLanguage(Locale language) {
		this.language = language;
	}

	public void setProficiencyLevel(ProficiencyLevel proficiencyLevel) {
		this.proficiencyLevel = proficiencyLevel;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Locale getLanguage() {
		return language;
	}

	public ProficiencyLevel getProficiencyLevel() {
		return proficiencyLevel;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", language=" + language + ", proficiencyLevel="
				+ proficiencyLevel + "]";
	}

}
