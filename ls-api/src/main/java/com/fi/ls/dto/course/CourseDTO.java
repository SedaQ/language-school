package com.fi.ls.dto.course;

import java.util.ArrayList;
import java.util.List;

import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.enums.ProficiencyLevel;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Pavel Šeda (441048)
 *
 */
public class CourseDTO {

	private Long id;
        @NotEmpty
	private String name;
        @NotEmpty
	private String language;
        @NotNull
	private ProficiencyLevel proficiencyLevel;
	private List<LectureDTO> listOfLectures = new ArrayList<>();

	public CourseDTO() {
	}

	public void addLecture(LectureDTO lectureDTO) {
		this.listOfLectures.add(lectureDTO);
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

	public void setListOfLectures(List<LectureDTO> listOfLectures) {
		this.listOfLectures = listOfLectures;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getLanguage() {
		return language;
	}

	public String getName() {
		return name;
	}

	public ProficiencyLevel getProficiencyLevel() {
		return proficiencyLevel;
	}

	public List<LectureDTO> getListOfLectures() {
		return listOfLectures;
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
		if (!(obj instanceof CourseDTO))
			return false;
		CourseDTO other = (CourseDTO) obj;
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
		return "CourseDTO [id=" + id + ", name=" + name + ", language=" + language + ", proficiencyLevel="
				+ proficiencyLevel + ", listOfLectures=" + listOfLectures + "]";
	}

}
