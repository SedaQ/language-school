package com.fi.ls.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dozer.Mapping;

import com.fi.ls.enums.ProficiencyLevel;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Entity
@Table(name = "course")
@NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_course")
	private Long id;

	@NotNull
	@Column(unique = true)
	private String name;

	@NotNull
	private String language;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "proficiency_level")
	private ProficiencyLevel proficiencyLevel;

	@ManyToMany(fetch = FetchType.LAZY)
	@Column(name = "list_of_lectures")
	@Mapping("listOfLectures")
	private List<Lecture> listOfLectures = new ArrayList<>();

	public Course() {
	}

	public void addLecture(Lecture lecture) {
		this.listOfLectures.add(lecture);
	}

	public void addLectures(List<Lecture> lectures) {
		lectures.forEach(l -> listOfLectures.add(l));
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setProficiencyLevel(ProficiencyLevel proficiencyLevel) {
		this.proficiencyLevel = proficiencyLevel;
	}

	public void setListOfLectures(List<Lecture> listOfLectures) {
		this.listOfLectures = listOfLectures;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLanguage() {
		return language;
	}

	public ProficiencyLevel getProficiencyLevel() {
		return proficiencyLevel;
	}

	public List<Lecture> getListOfLectures() {
		return Collections.unmodifiableList(listOfLectures);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((proficiencyLevel == null) ? 0 : proficiencyLevel.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Course))
			return false;
		Course other = (Course) obj;
		if (language == null) {
			if (other.getLanguage() != null)
				return false;
		} else if (!language.equals(other.getLanguage()))
			return false;
		if (name == null) {
			if (other.getName() != null)
				return false;
		} else if (!name.equals(other.getName()))
			return false;
		if (proficiencyLevel != other.getProficiencyLevel())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", language=" + language + ", proficiencyLevel="
				+ proficiencyLevel + ", listOfLectures=" + listOfLectures + "]";
	}

}
