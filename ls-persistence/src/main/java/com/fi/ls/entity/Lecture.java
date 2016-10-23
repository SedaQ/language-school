package com.fi.ls.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Marek Nedbal (357293)
 */
@Entity
@NamedQuery(name = "Lecture.findAll", query = "SELECT l FROM Lecture l")
public class Lecture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
        
        @NotNull
        private LocalDateTime dayTime;
        
        @NotNull
        private String topic;
        
        @ManyToMany(targetEntity = Student.class, mappedBy = "listOfLectures")        
        private List<Student> listOfStudents = new ArrayList<>();
        
        @ManyToMany(targetEntity = Lecturer.class, mappedBy = "listOfLectures")
        private List<Lecturer> listOfLecturers = new ArrayList<>();
        
        @ManyToMany(targetEntity = Course.class, mappedBy = "listOfLectures")
        private List<Course> listOfCourses = new ArrayList<>();
        
	public Lecture() {
            
	}
        
        public Long getId() {
            
            return this.id;
            
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
        
        public void addLecturer (Lecturer lecturer) {
            
            this.listOfLecturers.add(lecturer);
            
        }
        
        public void addCourse (Course course) {
            
            this.listOfCourses.add(course);
            
        }
        
        @Override
        public int hashCode() {
            
            return 31 + Long.valueOf(id).hashCode();
            
        }
        
        @Override
        public boolean equals(Object obj) {
                
            if (this == obj) {
                
                return true;
                
            }
            if (obj == null) {
                
                return false;
                
            }
            if (!(obj instanceof Lecture)) {
                
                return false;
                
            }
            Lecture other = (Lecture) obj;
            if (this.id == null) {
                
		if (other.id != null) {
                    
                    return false;
                    
                }				
            } else {
		
                if (!this.id.equals(other.id)) {
                    
                    return false;
                    
                }
				
            }
            return true;
            
        }
        
        @Override
        public String toString() {
            
            return "Lecture [id = " + id + ", dayTime = " + dayTime.toString() + ", topic = " + topic 
                    + ", listOfStudents: " + listOfStudents.toString() + ", listOfLecturers: "
                    + listOfLecturers.toString() + ", listOfCourses: " + listOfCourses.toString() + "]";
            
        }
        
}
