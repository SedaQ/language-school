package com.fi.ls.service;

import com.fi.ls.dao.StudentDao;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Student;
import com.fi.ls.exceptions.ServiceLayerException;
import com.fi.ls.security.UserPasswordEncryption;

import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Matúš
 */
@Service
public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao;

	private UserPasswordEncryption userPasswordEncryption;

	@Inject
	public StudentServiceImpl(StudentDao studentDao, UserPasswordEncryption userPasswordEncryption) {
		this.studentDao = studentDao;
		this.userPasswordEncryption = userPasswordEncryption;
	}

	@Override
	public Student create(Student s) {
		if (s == null)
			throw new IllegalArgumentException("Student is null.");
		try {
			this.studentDao.create(s);
			return s;
		} catch (PersistenceException | ConstraintViolationException | DataAccessException ex) {
			throw new ServiceLayerException("Creating student failed.", ex);
		}
	}

	@Override
	public void remove(Student s) {
		if (s == null)
			throw new IllegalArgumentException("Student is null.");
		try {
			this.studentDao.remove(s);
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Removing student failed.", ex);
		}
	}

	@Override
	public Student update(Student s) {
		if (s == null)
			throw new IllegalArgumentException("Student is null.");
		try {
			return this.studentDao.update(s);
		} catch (PersistenceException | ConstraintViolationException | DataAccessException ex) {
			throw new ServiceLayerException("Updating student failed.", ex);
		}
	}

	@Override
	public Student findByBirthNumber(String birthNumber) {
		if (birthNumber == null)
			throw new IllegalArgumentException("Id is null");
		try {
			return this.studentDao.findByBirthNumber(birthNumber);
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Student cannot be found by birth number.", ex);
		}
	}

	@Override
	public Student findById(Long id) {
		if (id == null)
			throw new IllegalArgumentException("Id is null");
		try {
			return this.studentDao.findById(id);
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Student cannot be found by id.", ex);
		}
	}

	@Override
	public Set<Student> findAllStudents() {
		try {
			return this.studentDao.findAll();
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Find all students failed.", ex);
		}
	}

	@Override
	public Set<Student> findByFirstName(String firstName) {
		if (firstName == null)
			throw new IllegalArgumentException("First name is null.");
		try {
			return this.studentDao.findByFirstName(firstName);
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Find students by first name failed.", ex);
		}
	}

	@Override
	public Set<Student> findBySurname(String surname) {
		if (surname == null)
			throw new IllegalArgumentException("Surname is null.");
		try {
			return this.studentDao.findBySurname(surname);
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Find students by surname failed.", ex);
		}
	}

	@Override
	public void enrollCourse(Course c, Student s) {
		if (c == null || s == null)
			throw new IllegalArgumentException("Course or student is null.");
		s.addListOfLectures(c.getListOfLectures());
		this.studentDao.update(s);
	}

	@Override
	public void enrollLecture(Lecture l, Student s) {
		if (l == null || s == null)
			throw new IllegalArgumentException("Lecture or student is null.");
		s.addLecture(l);
		this.studentDao.update(s);
	}

	@Override
	public void cancelLectureFromStudentsList(Lecture l, Student s) {
		if (l == null || s == null)
			throw new IllegalArgumentException("Lecture or student is null.");
		s.removeLecture(l);
		this.studentDao.update(s);
	}

	@Override
	public void cancelListOfLecturesFromStudentsList(Set<Lecture> l, Student s) {
		if (l == null || s == null)
			throw new IllegalArgumentException("Lecture or student is null.");
		s.removeListOfLectures(l);
		this.studentDao.update(s);
	}

	@Override
	public Boolean registerUser(Student u, String unencryptedPassword) {
		if (u == null)
			throw new IllegalArgumentException("Student u parameter is null");
		if (unencryptedPassword == null)
			throw new IllegalArgumentException("String unencryptedPassword parameter is null");

		try {
			u.setPasswordHash(userPasswordEncryption.createHash(unencryptedPassword));
			studentDao.create(u);
		} catch (RuntimeException ex) {
			throw new ServiceLayerException("Problem with registering Lecturer, see inner exception.", ex);
		}
		return Boolean.TRUE;
	}

	@Override
	public boolean authenticate(Student u, String password) {
		if (u == null)
			throw new IllegalArgumentException("Student u parameter is null");
		if (password == null)
			throw new IllegalArgumentException("String password parameter is null");

		try {
			return userPasswordEncryption.validatePassword(password, u.getPasswordHash());
		} catch (RuntimeException ex) {
			throw new ServiceLayerException("Problem with authenticating Student, see inner exception.", ex);
		}
	}

}