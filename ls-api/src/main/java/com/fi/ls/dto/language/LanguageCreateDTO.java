package com.fi.ls.dto.language;

import com.fi.ls.enums.ProficiencyLevel;

/**
 *
 * @author Lukas Daubner (410034)
 */
public class LanguageCreateDTO {
    
    private String language;
    private Long lecturerId;
    private ProficiencyLevel proficiencyLevel;
        
    public String getLanguage() {
            return language;
    }

    public void setLanguage(String language) {
            this.language = language;
    }

    public Long getLecturerId() {
            return lecturerId;
    }

    public void setLecturer(Long lecturerId) {
            this.lecturerId = lecturerId;
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
        if (!(obj instanceof LanguageCreateDTO))
            return false;
        LanguageCreateDTO other = (LanguageCreateDTO) obj;
        if (this.language == null) {
            if (other.getLanguage() != null)
                return false;
        } else {
            if (!this.language.equals(other.getLanguage()))
                return false;
        }
        if (this.lecturerId == null) {
            if (other.getLecturerId() != null)
                return false;
        } else {
            if (!this.lecturerId.equals(other.getLecturerId()))
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
        return (language == null ? 0 : language.hashCode()) + 2 ^ (lecturerId == null ? 0 : lecturerId.hashCode());
    }
}
