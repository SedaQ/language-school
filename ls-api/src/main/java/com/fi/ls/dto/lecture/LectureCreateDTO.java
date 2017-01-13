package com.fi.ls.dto.lecture;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Pavel Šeda (441048) + Marek Nedbal (357293)
 *
 */
public class LectureCreateDTO {
        
        private Long id;
    
	@NotNull
	//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
	private LocalDateTime dayTime;

	@NotNull
	private String classroomId;

	@NotNull
	private String topic;

        public Long getId() {

		return id;

	}

	public void setId(Long id) {

		this.id = id;

	}
        
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
