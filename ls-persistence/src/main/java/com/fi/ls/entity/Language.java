package com.fi.ls.entity;

import com.fi.ls.enums.ProficiencyLevel;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lukas Daubner (410034)
 */
@Entity
public class Language {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String language;
    
    @NotNull
    @ManyToOne
    private Lecturer lecturer;
    
    @NotNull
    private ProficiencyLevel cefrLever;
    
    //<editor-fold defaultstate="collapsed" desc="GET/SET">

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

    public ProficiencyLevel getCefrLever() {
        return cefrLever;
    }

    public void setCefrLever(ProficiencyLevel cefrLever) {
        this.cefrLever = cefrLever;
    }
    
    //</editor-fold>

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(this == null)
            return false;
        if(!(obj instanceof Language))
            return false;
        Language other = (Language)obj;
        if(this.language == null)
        {
            if(other.language != null)
                return false;
        }
        else
        {
             if(!this.language.equals(other.language))
                 return false;
        }
        if(this.lecturer == null)
        {
            if(other.lecturer != null)
                return false;
        }
        else
        {
             if(!this.lecturer.equals(other.lecturer))
                 return false;
        }
        if(this.cefrLever == null)
        {
            if(other.cefrLever != null)
                return false;
        }
        else
        {
            if(!this.cefrLever.equals(other.cefrLever))
                 return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (language == null ? 0 : language.hashCode()) + 2^(lecturer == null ? 0 : lecturer.hashCode());
    }
}
