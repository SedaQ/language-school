package com.fi.ls.dto.lecture;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecturer.LecturerDTO;
import com.fi.ls.dto.student.StudentDTO;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author Marek Nedbal (357293)
 */
public class LectureDTO {

    private Long id;
    
    @NotNull(message= "Time couldn't be empty")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dayTime;
    
    @NotEmpty
    private String classroomId;

    @NotEmpty
    private String topic;
    
    private List<StudentDTO> listOfStudents = new ArrayList<>();

    private List<LecturerDTO> listOfLecturers = new ArrayList<>();

    private List<CourseDTO> listOfCourses = new ArrayList<>();

    public LectureDTO() {

    }

    public Long getId() {

	return this.id;

    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public LocalDateTime getDayTime() {

    	return this.dayTime;

    }

    public void setDayTime(LocalDateTime newDayTime) {

    	this.dayTime = newDayTime;

    }
    
    public String getClassroomId() {

	return this.classroomId;

    }

    public void setClassroomId(String newClassroomId) {

	this.classroomId = newClassroomId;

    }

    public String getTopic() {

	return this.topic;

    }

    public void setTopic(String newTopic) {

	this.topic = newTopic;

    }

    public List<StudentDTO> getListOfStudents() {

    	return this.listOfStudents;

    }

    public void setListOfStudents(List<StudentDTO> newListOfStudents) {

	this.listOfStudents = newListOfStudents;

    }
    
    public void addStudent(StudentDTO student) {

	this.listOfStudents.add(student);

    }

    public List<LecturerDTO> getListOfLecturers() {

	return this.listOfLecturers;

    }

    public void setListOfLecturers(List<LecturerDTO> newListOfLecturers) {

	this.listOfLecturers = newListOfLecturers;

    }
    
    public void addLecturer(LecturerDTO lecturer) {

    	this.listOfLecturers.add(lecturer);

    }
    
    public List<CourseDTO> getListOfCourses() {

	return this.listOfCourses;

    }

    public void setListOfCourses(List<CourseDTO> newListOfCourses) {

	this.listOfCourses = newListOfCourses;

    }

    public void addCourse(CourseDTO course) {

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
	if (!(obj instanceof LectureDTO)) {
            return false;
	}
	final LectureDTO other = (LectureDTO) obj;
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
