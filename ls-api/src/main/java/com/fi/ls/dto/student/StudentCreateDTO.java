package com.fi.ls.dto.student;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Pavel Šeda (441048)
 *
 */
public class StudentCreateDTO {

	private Long id;

	@NotNull
	@Size(min = 2, max = 50)
	private String firstName;

	@NotNull
	@Size(min = 2, max = 50)
	private String surname;

	@NotNull
	private String birthNumber;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthNumber() {
		return birthNumber;
	}

	public void setBirthNumber(String birthNumber) {
		this.birthNumber = birthNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthNumber == null) ? 0 : birthNumber.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StudentCreateDTO))
			return false;
		StudentCreateDTO other = (StudentCreateDTO) obj;
		if (birthNumber == null) {
			if (other.birthNumber != null)
				return false;
		} else if (!birthNumber.equals(other.getBirthNumber()))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.getFirstName()))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.getSurname()))
			return false;
		return true;
	}

}
