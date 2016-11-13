package com.fi.ls.dto;

import com.fi.ls.enums.ProficiencyLevel;
import java.util.Locale;

/**
 *
 * @author Lukas Daubner (410034)
 */
public class LanguageDTO {
    
    private Long id;
    private String language;
    private LecturerDTO lecturer;
    private ProficiencyLevel proficiencyLevel;
    
    public Long getId() {
            return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
        
    public String getLanguage() {
            return language;
    }

    public void setLanguage(String language) {
            this.language = language;
    }

    public LecturerDTO getLecturer() {
            return lecturer;
    }

    public void setLecturer(LecturerDTO lecturerDTO) {
            this.lecturer = lecturerDTO;
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
        if (!(obj instanceof LanguageDTO))
            return false;
        LanguageDTO other = (LanguageDTO) obj;
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
