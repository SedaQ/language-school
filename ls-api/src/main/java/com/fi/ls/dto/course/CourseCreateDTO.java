package com.fi.ls.dto.course;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fi.ls.enums.ProficiencyLevel;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Pavel Šeda (441048)
 *
 */
public class CourseCreateDTO {

	@NotEmpty(message="Please enter name")
	@Size(min = 2, max = 30, message="Length must be between {min} and {max} ")
	private String name;

	@NotEmpty(message="Please enter language")
	private String language;

	@NotNull(message="Please enter proficiencyLevel")
	private ProficiencyLevel proficiencyLevel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public ProficiencyLevel getProficiencyLevel() {
		return proficiencyLevel;
	}

	public void setProficiencyLevel(ProficiencyLevel proficiencyLevel) {
		this.proficiencyLevel = proficiencyLevel;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CourseCreateDTO))
			return false;
		CourseCreateDTO other = (CourseCreateDTO) obj;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.getLanguage()))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.getName()))
			return false;
		if (proficiencyLevel != other.getProficiencyLevel())
			return false;
		return true;
	}
	
}
