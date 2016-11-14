package com.fi.ls.service;

import com.fi.ls.dao.StudentDao;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Student;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Matúš
 */
@Service
public class StudentServiceImpl implements StudentService {

	
	private StudentDao studentDao;

	@Inject
	public StudentServiceImpl(StudentDao studentDao){
		this.studentDao = studentDao;
	}
	
	@Override
	public Student findByBirthNumber(String birthNumber) {
		return studentDao.findByBirthNumber(birthNumber);
	}

	@Override
	public Student findByEmail(String email) {
		return studentDao.findByEmail(email);
	}

	@Override
	public Student findById(Long id) {
		return studentDao.findById(id);
	}

	@Override
	public List<Student> findAllStudents() {
		return studentDao.findAll();
	}

	@Override
	public List<Student> findByFirstName(String firstName) {
		return studentDao.findByFirstName(firstName);
	}

	@Override
	public List<Student> findBySurname(String surname) {
		return studentDao.findBySurname(surname);
	}

	@Override
	public Student update(Student s) {
		return studentDao.update(s);
	}

	@Override
	public void remove(Student s) {
		studentDao.remove(s);
	}

	@Override
	public void registerStudent(Student s, String unencryptedPassword) {
		s.setPasswordHash(UserPasswordEncryption.createHash(unencryptedPassword));
		studentDao.create(s);
	}

	@Override
	public boolean authenticateStudent(Student s, String password) {
		return UserPasswordEncryption.validatePassword(password, s.getPasswordHash());
	}

	@Override
	public void enrollCourse(Course c, Student s) {
		c.getListOfLectures().forEach(lecture -> s.addLecture(lecture));
	}

	@Override
	public void enrollLecture(Lecture l, Student s) {
		s.addLecture(l);
	}

	@Override
	public void cancelLectureFromStudentsList(Lecture l, Student s) {
		s.removeLecture(l);
	}

	@Override
	public void cancelLecturesFromStudentsList(List<Lecture> l, Student s) {
		s.removeLectures(l);
	}

}
