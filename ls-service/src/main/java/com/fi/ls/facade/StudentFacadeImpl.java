package com.fi.ls.facade;

import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.student.StudentCreateDTO;
import com.fi.ls.dto.student.StudentDTO;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Student;
import com.fi.ls.exceptions.ServiceLayerException;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.StudentService;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Matúš
 */
@Service
@Transactional
public class StudentFacadeImpl implements StudentFacade {

	private final Logger logger = LoggerFactory.getLogger(StudentFacadeImpl.class);

	private StudentService studentService;
	private BeanMapping beanMapping;

	@Inject
	public StudentFacadeImpl(StudentService studentService, BeanMapping beanMapping) {
		this.studentService = studentService;
		this.beanMapping = beanMapping;
	}

	@Override
	public Optional<StudentDTO> createStudent(StudentCreateDTO s) {
		if (s == null)
			throw new IllegalArgumentException("Student is null");
		try {
			Optional<Student> student = Optional.of(studentService.create(beanMapping.mapTo(s, Student.class).get()));
			return student.isPresent() ? beanMapping.mapTo(student.get(), StudentDTO.class) : Optional.empty();
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("error in create student: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public Optional<StudentDTO> updateStudent(StudentDTO s) {
		if (s == null)
			throw new IllegalArgumentException("Student is null");
		try {
			Optional<Student> student = Optional.of(studentService.findById(s.getId()));
			return student.isPresent() ? beanMapping.mapTo(student.get(), StudentDTO.class) : Optional.empty();
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("error in update student: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public void deleteStudent(StudentDTO s) {
		if (s == null)
			throw new IllegalArgumentException("Student is null");
		try {
			this.studentService.remove(studentService.findById(s.getId()));
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("error in delete student: " + ex);
		}
	}

	@Override
	public Optional<StudentDTO> getStudentById(Long id) {
		if (id == null)
			throw new IllegalArgumentException("Id is null");
		try {
			Optional<Student> student = Optional.of(studentService.findById(id));
			return student.isPresent() ? beanMapping.mapTo(student.get(), StudentDTO.class) : Optional.empty();
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("error in get student by id: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public List<StudentDTO> getAllStudents() {
		try {
			return beanMapping.mapTo(studentService.findAllStudents(), StudentDTO.class);
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("error in get all students: " + ex);
			return Collections.emptyList();
		}
	}

	@Override
	public Optional<StudentDTO> getStudentByBirthNumber(String birthNumber) {
		if (birthNumber == null)
			throw new IllegalArgumentException("Birth number is null");
		try {
			Optional<Student> student = Optional.of(studentService.findByBirthNumber(birthNumber));
			return student.isPresent() ? beanMapping.mapTo(student.get(), StudentDTO.class) : Optional.empty();
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("error in get student by birth number: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public List<StudentDTO> getStudentsByFirstName(String firstName) {
		if (firstName == null)
			throw new IllegalArgumentException("First name is null");
		try {
			return beanMapping.mapTo(studentService.findByFirstName(firstName), StudentDTO.class);
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("error in get all students by first name: " + ex);
			return Collections.emptyList();
		}
	}

	@Override
	public List<StudentDTO> getStudentsBySurname(String surname) {
		if (surname == null)
			throw new IllegalArgumentException("Surname is null");
		try {
			return beanMapping.mapTo(studentService.findBySurname(surname), StudentDTO.class);
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("error in get all students by first name: " + ex);
			return Collections.emptyList();
		}
	}

	@Override
	public void enrollCourse(CourseDTO c, StudentDTO s) {
		if (c == null || s == null)
			throw new IllegalArgumentException("course or student is null");
		try {
			studentService.enrollCourse(beanMapping.mapTo(c, Course.class).get(),
					beanMapping.mapTo(s, Student.class).get());
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("error in enroll course: " + ex);
		}
	}

	@Override
	public void enrollLecture(LectureDTO l, StudentDTO s) {
		if (l == null || s == null)
			throw new IllegalArgumentException("course or student is null");
		try {
			studentService.enrollLecture(beanMapping.mapTo(l, Lecture.class).get(),
					beanMapping.mapTo(s, Student.class).get());
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("error in enroll lecture: " + ex);
		}
	}

	@Override
	public void cancelLectureFromStudentsList(LectureDTO l, StudentDTO s) {
		if (l == null || s == null)
			throw new IllegalArgumentException("course or student is null");
		try {
			studentService.cancelLectureFromStudentsList(beanMapping.mapTo(l, Lecture.class).get(),
					beanMapping.mapTo(s, Student.class).get());
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("error in cancel lecture: " + ex);
		}
	}

	@Override
	public void cancelListOfLecturesFromStudentsList(List<LectureDTO> l, StudentDTO s) {
		if (l == null || s == null)
			throw new IllegalArgumentException("course or student is null");
		try {
			studentService.cancelListOfLecturesFromStudentsList(beanMapping.mapTo(l, Lecture.class),
					beanMapping.mapTo(s, Student.class).get());
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("error in cancel list of lectures: " + ex);
		}
	}

}
