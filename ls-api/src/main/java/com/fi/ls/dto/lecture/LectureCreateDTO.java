package com.fi.ls.dto.lecture;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Pavel Šeda (441048) + Marek Nedbal (357293)
 *
 */
public class LectureCreateDTO {

    @NotNull
    private LocalDateTime dayTime;

    @NotNull
    @Size(min = 1)
    private String classroomId;

    @NotNull
    @Size(min = 2)
    private String topic;

    public LocalDateTime getDayTime() {
        
    	return dayTime;
        
    }

    public void setDayTime(LocalDateTime dayTime) {
        
	this.dayTime = dayTime;
        
    }

    public String getClassroomId() {
        
	return classroomId;
        
    }

    public void setClassroomId(String classroomId) {
	
        this.classroomId = classroomId;
        
    }

    public String getTopic() {
        
	return topic;
        
    }

    public void setTopic(String topic) {
        
	this.topic = topic;
        
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

}
