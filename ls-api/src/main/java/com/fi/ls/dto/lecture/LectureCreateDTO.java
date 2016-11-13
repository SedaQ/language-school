package com.fi.ls.dto.lecture;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Pavel Šeda (441048)
 *
 */
public class LectureCreateDTO {

	@NotNull
	@Future
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classroomId == null) ? 0 : classroomId.hashCode());
		result = prime * result + ((dayTime == null) ? 0 : dayTime.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LectureCreateDTO))
			return false;
		LectureCreateDTO other = (LectureCreateDTO) obj;
		if (classroomId == null) {
			if (other.classroomId != null)
				return false;
		} else if (!classroomId.equals(other.getClassroomId()))
			return false;
		if (dayTime == null) {
			if (other.dayTime != null)
				return false;
		} else if (!dayTime.equals(other.getDayTime()))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.getTopic()))
			return false;
		return true;
	}

}
