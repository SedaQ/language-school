package com.fi.ls.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Matúš
 */
@Entity
@NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String surname;
        
    @NotNull
    @Column(unique = true)
    private String birthNumber;

    @ManyToMany
    private List<Lecture> listOfLectures = new ArrayList<>();

    public Student() {
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setSurname(String surname) {
    	this.surname = surname;
    }

    public void setListOfLectures(List<Lecture> listOfLectures) {
    	this.listOfLectures = listOfLectures;
    }

    public void addLecture(Lecture lecture) {
	this.listOfLectures.add(lecture);
    }

    public Long getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public String getSurname() {
	return surname;
    }

    public List<Lecture> getListOfLectures() {
    	return listOfLectures;
    }

    public String getBirthNumber() {
        return birthNumber;
    }

    public void setBirthNumber(String birthNumber) {
        this.birthNumber = birthNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.birthNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Student)) {
            return false;
        }
        final Student other = (Student) obj;
        if (!Objects.equals(this.birthNumber, other.birthNumber)) {
            return false;
        }
        return true;
    }
}