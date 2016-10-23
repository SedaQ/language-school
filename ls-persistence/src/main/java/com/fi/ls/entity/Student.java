package com.fi.ls.entity;

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
	@Column(unique = true)
	private String name;

	@ManyToMany
	private List<Lecture> listOfLectures;

	public Student() {
	}

        // <editor-fold defaultstate="collapsed" desc="GET/SET">
	public void setName(String name) {
		this.name = name;
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

	public List<Lecture> getListOfLectures() {
		return listOfLectures;
	}
        // </editor-fold>

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
        
}