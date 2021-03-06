package com.fi.ls.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dozer.Mapping;

/**
 *
 * @author Matúš
 */
@Entity
@Table(name = "student")
@NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
public class Student extends LSUser {

    @NotNull
    private String firstName;

    @NotNull
    private String surname;

    @NotNull
    @Column(unique = true, name = "birth_number")
    private String birthNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "list_of_lectures")
    @Mapping("listOfLectures")
    private Set<Lecture> listOfLectures = new HashSet<>();

    public Student() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
    	this.surname = surname;
    }

    public void setListOfLectures(Set<Lecture> listOfLectures) {
    	this.listOfLectures = listOfLectures;
    }

    public void setBirthNumber(String birthNumber) {
    	this.birthNumber = birthNumber;
    }

    public String getFirstName() {
    	return firstName;
    }

    public String getSurname() {
    	return surname;
    }

    public Set<Lecture> getListOfLectures() {
    	return listOfLectures;
    }

    public String getBirthNumber() {
	return birthNumber;
    }

    public void addLecture(Lecture lecture) {
    	if (!(this.listOfLectures.contains(lecture))) this.listOfLectures.add(lecture);
    }

    public void addListOfLectures(Set<Lecture> lectures) {
    	lectures.forEach(l -> addLecture(l));
    }

    public void removeLecture(Lecture lecture) {
	this.listOfLectures.remove(lecture);
    }

    public void removeListOfLectures(Set<Lecture> lectures) {
	lectures.forEach(l -> removeLecture(l));
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
	if (!Objects.equals(this.birthNumber, other.getBirthNumber())) {
            return false;
	}
	return true;
    }
    
}