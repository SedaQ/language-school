package com.fi.ls.entity;

import com.fi.ls.enums.ProficiencyLevel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lukas Daubner (410034)
 */
@Entity
@Table(name = "language")
@NamedQuery(name = "Language.findAll", query = "SELECT l FROM Language l")
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String language;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Lecturer lecturer;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "proficiency_level")
	private ProficiencyLevel proficiencyLevel;

	public Long getId() {
		return id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public ProficiencyLevel getProficiencyLevel() {
		return proficiencyLevel;
	}

	public void setProficiencyLevel(ProficiencyLevel proficiencyLevel) {
		this.proficiencyLevel = proficiencyLevel;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Language))
			return false;
		Language other = (Language) obj;
		if (this.language == null) {
			if (other.getLanguage() != null)
				return false;
		} else {
			if (!this.language.equals(other.getLanguage()))
				return false;
		}
		if (this.lecturer == null) {
			if (other.getLecturer() != null)
				return false;
		} else {
			if (!this.lecturer.equals(other.getLecturer()))
				return false;
		}
		if (this.proficiencyLevel == null) {
			if (other.getProficiencyLevel() != null)
				return false;
		} else {
			if (!this.proficiencyLevel.equals(other.getProficiencyLevel()))
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return (language == null ? 0 : language.hashCode()) + 2 ^ (lecturer == null ? 0 : lecturer.hashCode());
	}
}
