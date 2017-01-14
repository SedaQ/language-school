package com.fi.ls.dto.lecturer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.user.LSUserDTO;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Lukas Daubner (410034)
 */
public class LecturerDTO extends LSUserDTO {
    @NotEmpty
    private String nickname;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String surname;
    private List<LanguageDTO> listOfLanguages = new ArrayList<>();
    private List<LectureDTO> listOfLectures = new ArrayList<>();

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
    
    public Long getId(){
    	return super.getId();
    }

    public List<LanguageDTO> getListOfLanguages() {
            return Collections.unmodifiableList(listOfLanguages);
    }

    public void setListOfLanguages(List<LanguageDTO> listOfLanguages) {
        this.listOfLanguages = listOfLanguages;
    }

    public List<LectureDTO> getListOfLectures() {
            return Collections.unmodifiableList(listOfLectures);
    }

    public void setListOfLectures(List<LectureDTO> listOfLectures) {
        this.listOfLectures = listOfLectures;
    }

    public void addLanguage(LanguageDTO languageDTO) {
            listOfLanguages.add(languageDTO);
            languageDTO.setLecturer(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof LecturerDTO))
            return false;
        LecturerDTO other = (LecturerDTO) obj;
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
