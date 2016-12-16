package com.fi.ls.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dozer.Mapping;

/**
 * 
 * @author Marek Nedbal (357293)
 */
@Entity
@Table(name = "lecture")
@NamedQuery(name = "Lecture.findAll", query = "SELECT l FROM Lecture l")
public class Lecture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_lecture")
	private Long id;

	private LocalDateTime dayTime;

	@NotNull
	@Column(name = "classroom_id")
	private String classroomId;

	@NotNull
	private String topic;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Student.class, mappedBy = "listOfLectures")
	@Column(name = "list_of_students")
	@Mapping("listOfStudents")
	private List<Student> listOfStudents = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Lecturer.class, mappedBy = "listOfLectures")
	@Column(name = "list_of_lecturers")
	@Mapping("listOfLecturers")
	private List<Lecturer> listOfLecturers = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Course.class, mappedBy = "listOfLectures")
	@Column(name = "list_of_courses")
	@Mapping("listOfCourses")
	private List<Course> listOfCourses = new ArrayList<>();

	public Lecture() {

	}

	public Long getId() {

		return this.id;

	}

        public void setId(Long id) {
            this.id = id;
        }

	public String getClassroomId() {

		return this.classroomId;

	}

	public void setClassroomId(String newClassroomId) {

		this.classroomId = newClassroomId;

	}

	public LocalDateTime getDayTime() {

		return this.dayTime;

	}

	public void setDayTime(LocalDateTime newDayTime) {

		this.dayTime = newDayTime;

	}

	public String getTopic() {

		return this.topic;

	}

	public void setTopic(String newTopic) {

		this.topic = newTopic;

	}

	public List<Student> getListOfStudents() {

		return this.listOfStudents;

	}

	public void setListOfStudents(List<Student> newListOfStudents) {

		this.listOfStudents = newListOfStudents;

	}

	public List<Lecturer> getListOfLecturers() {

		return this.listOfLecturers;

	}

	public void setListOfLecturers(List<Lecturer> newListOfLecturers) {

		this.listOfLecturers = newListOfLecturers;

	}

	public List<Course> getListOfCourses() {

		return this.listOfCourses;

	}

	public void setListOfCourses(List<Course> newListOfCourses) {

		this.listOfCourses = newListOfCourses;

	}

	public void addStudent(Student student) {

		this.listOfStudents.add(student);

	}

	public void addLecturer(Lecturer lecturer) {

		this.listOfLecturers.add(lecturer);

	}

	public void addCourse(Course course) {

		this.listOfCourses.add(course);

	}

	@Override
	public int hashCode() {

		int hash = 7;
		hash = 67 * hash + Objects.hashCode(this.dayTime);
		hash = 67 * hash + Objects.hashCode(this.classroomId);
		return hash;

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Lecture)) {
			return false;
		}
		final Lecture other = (Lecture) obj;
		if (!Objects.equals(this.dayTime, other.getDayTime())) {
			return false;
		}
		return Objects.equals(this.classroomId, other.getClassroomId());

	}

	@Override
	public String toString() {

		return "Lecture [id = " + id + ", dayTime = " + dayTime.toString() + ", classId: " + classroomId + ", topic = "
				+ topic + ", listOfStudents: " + listOfStudents.toString() + ", listOfLecturers: "
				+ listOfLecturers.toString() + ", listOfCourses: " + listOfCourses.toString() + "]";

	}

}
