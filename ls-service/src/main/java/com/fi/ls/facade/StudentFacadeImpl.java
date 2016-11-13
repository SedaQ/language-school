package com.fi.ls.facade;

import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.student.StudentCreateDTO;
import com.fi.ls.dto.student.StudentDTO;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Student;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.StudentService;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Matúš
 */
@Service
@Transactional
public class StudentFacadeImpl implements StudentFacade {

	@Inject
	private StudentService studentService;

	@Inject
	private BeanMapping beanMapping;

	@Override
	public void registerStudent(StudentCreateDTO s, String unencryptedPassword) {
		Student studentEntity = beanMapping.mapTo(s, Student.class).get();
		studentService.registerStudent(studentEntity, unencryptedPassword);
		s.setId(studentEntity.getId());
	}

	@Override
	public Optional<StudentDTO> getStudentByEmail(String email) {
		Optional<Student> student = Optional.of(studentService.findByEmail(email));
		return student.isPresent() ? beanMapping.mapTo(student.get(), StudentDTO.class) : Optional.empty();
	}

	@Override
	public Optional<StudentDTO> getStudentById(Long id) {
		Optional<Student> student = Optional.of(studentService.findById(id));
		return student.isPresent() ? beanMapping.mapTo(student.get(), StudentDTO.class) : Optional.empty();
	}

	@Override
	public List<StudentDTO> getAllStudents() {
		return beanMapping.mapTo(studentService.findAllStudents(), StudentDTO.class);
	}

	@Override
	public Optional<StudentDTO> updateStudent(StudentDTO s) {
		Optional<Student> student = Optional.of(studentService.findById(s.getId()));
		return student.isPresent() ? beanMapping.mapTo(student.get(), StudentDTO.class) : Optional.empty();
	}

	@Override
	public void deleteStudent(StudentDTO s) {
		this.studentService.remove(studentService.findById(s.getId()));
	}

	@Override
	public boolean authenticateStudent(StudentDTO s) {
		return studentService.authenticateStudent(studentService.findById(s.getId()), s.getPasswordHash());
	}

	@Override
	public Optional<StudentDTO> getStudentByBirthNumber(String birthNumber) {
		Optional<Student> student = Optional.of(studentService.findByBirthNumber(birthNumber));
		return student.isPresent() ? beanMapping.mapTo(student.get(), StudentDTO.class) : Optional.empty();
	}

	@Override
	public List<StudentDTO> getStudentsByFirstName(String firstName) {
		return beanMapping.mapTo(studentService.findByFirstName(firstName), StudentDTO.class);
	}

	@Override
	public List<StudentDTO> getStudentsBySurname(String surname) {
		return beanMapping.mapTo(studentService.findBySurname(surname), StudentDTO.class);
	}

	@Override
	public void enrollCourse(CourseDTO c, StudentDTO s) {
		studentService.enrollCourse(beanMapping.mapTo(c, Course.class).get(),
				beanMapping.mapTo(s, Student.class).get());
	}

	@Override
	public void enrollLecture(LectureDTO l, StudentDTO s) {
		studentService.enrollLecture(beanMapping.mapTo(l, Lecture.class).get(),
				beanMapping.mapTo(s, Student.class).get());
	}

	@Override
	public void cancelLectureFromStudentsList(LectureDTO l, StudentDTO s) {
		studentService.cancelLectureFromStudentsList(beanMapping.mapTo(l, Lecture.class).get(),
				beanMapping.mapTo(s, Student.class).get());
	}

	@Override
	public void cancelLecturesFromStudentsList(List<LectureDTO> l, StudentDTO s) {
		studentService.cancelLecturesFromStudentsList(beanMapping.mapTo(l, Lecture.class),
				beanMapping.mapTo(s, Student.class).get());
	}

}
