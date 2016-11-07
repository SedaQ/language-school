package com.fi.ls.dto;

import java.util.ArrayList;
import java.util.List;

import com.fi.ls.enums.ProficiencyLevel;

/**
 * @author Pavel Šeda (441048)
 *
 */
public class CourseDTO extends LSUserDTO {

	private String name;
	private String language;
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

}
