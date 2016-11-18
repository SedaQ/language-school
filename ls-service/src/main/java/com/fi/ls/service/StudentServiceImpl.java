package com.fi.ls.service;

import com.fi.ls.dao.StudentDao;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Student;
import com.fi.ls.exceptions.ServiceLayerException;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.jpa.JpaSystemException;
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
    public Student create(Student s) {
        if (s == null) throw new IllegalArgumentException("Student is null.");
        try {
            this.studentDao.create(s);
            return s;
        } catch (PersistenceException | 
                InvalidDataAccessApiUsageException | 
                ConstraintViolationException |
                JpaSystemException ex) {
            throw new ServiceLayerException("Creating student failed.", ex);
	}
    }
    
    @Override
    public void remove(Student s) {
        if (s == null) throw new IllegalArgumentException("Student is null.");
        try {
            this.studentDao.remove(s);
        } catch (PersistenceException | 
                InvalidDataAccessApiUsageException | 
                JpaSystemException ex) {
            throw new ServiceLayerException("Removing student failed.", ex);
	}
    }

    @Override
    public Student update(Student s) {
        if (s == null) throw new IllegalArgumentException("Student is null.");
        try {
            return this.studentDao.update(s);
        } catch (PersistenceException | 
                InvalidDataAccessApiUsageException | 
                ConstraintViolationException |
                JpaSystemException ex) {
            throw new ServiceLayerException("Updating student failed.", ex);
	}
    }

    @Override
    public Student findByBirthNumber(String birthNumber) {
        if (birthNumber == null) throw new IllegalArgumentException("Id is null");
	try {
            return this.studentDao.findByBirthNumber(birthNumber);
        } catch (PersistenceException | 
                InvalidDataAccessApiUsageException | 
                JpaSystemException ex) {
            throw new ServiceLayerException("Student cannot be found by birth number.", ex);
	}
    }

    @Override
    public Student findById(Long id) {
        if (id == null) throw new IllegalArgumentException("Id is null");
	try {
            return this.studentDao.findById(id);
        } catch (PersistenceException | 
                InvalidDataAccessApiUsageException | 
                JpaSystemException ex) {
            throw new ServiceLayerException("Student cannot be found by id.", ex);
	}
    }

    @Override
    public List<Student> findAllStudents() {
        try {
            return this.studentDao.findAll();
	} catch (PersistenceException | 
                JpaSystemException ex) {
            throw new ServiceLayerException("Find all students failed.", ex);
	}
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        if (firstName == null) throw new IllegalArgumentException("First name is null.");
        try {
            return this.studentDao.findByFirstName(firstName);
	} catch (PersistenceException | 
                InvalidDataAccessApiUsageException | 
                JpaSystemException ex) {
            throw new ServiceLayerException("Find students by fisrt name failed.", ex);
	}
    }

    @Override
    public List<Student> findBySurname(String surname) {
        if (surname == null) throw new IllegalArgumentException("Surname is null.");
        try {
            return this.studentDao.findBySurname(surname);
	} catch (PersistenceException | 
                InvalidDataAccessApiUsageException | 
                JpaSystemException ex) {
            throw new ServiceLayerException("Find students by surname failed.", ex);
	}
    }

    @Override
    public void enrollCourse(Course c, Student s) {
        if (c == null || s == null) throw new IllegalArgumentException("Course or student is null.");
        s.addListOfLectures(c.getListOfLectures());
        this.update(s);
    }

    @Override
    public void enrollLecture(Lecture l, Student s) {
        if (l == null || s == null) throw new IllegalArgumentException("Lecture or student is null.");
        s.addLecture(l);
        this.update(s);
    }

    @Override
    public void cancelLectureFromStudentsList(Lecture l, Student s) {
        if (l == null || s == null) throw new IllegalArgumentException("Lecture or student is null.");
        s.removeLecture(l);
        this.update(s);
    }

    @Override
    public void cancelListOfLecturesFromStudentsList(List<Lecture> l, Student s) {
        if (l == null || s == null) throw new IllegalArgumentException("Lecture or student is null.");
        s.removeListOfLectures(l);
        this.update(s);
    }

}
