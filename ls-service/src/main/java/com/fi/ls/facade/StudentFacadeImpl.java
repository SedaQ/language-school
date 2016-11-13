package com.fi.ls.facade;

import com.fi.ls.dto.StudentDTO;
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
    public void registerStudent(StudentDTO s, String unecryptedPassword) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<StudentDTO> getStudentByBirthNumber(String birthNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<StudentDTO> getStudentByEmail(String email) {
        Optional<Student> student = Optional.of(studentService.findByEmail(email));
		return student.isPresent() ? beanMapping.mapTo(student.get(), StudentDTO.class) : Optional.empty();
    }

    @Override
    public Optional<StudentDTO> getStudentById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StudentDTO> getStudentsByFirstName(String firstName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StudentDTO> getStudentsBySurname(String surname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<StudentDTO> updateStudent(StudentDTO s) {
        Optional<Student> student = Optional.of(studentService.findById(s.getId()));
	return student.isPresent() ? beanMapping.mapTo(student.get(), StudentDTO.class) : Optional.empty();
    }

    @Override
    public void deleteStudent(StudentDTO s) {
        this.studentService.remove(this.studentService.findById(s.getId()));
    }

    @Override
    public boolean authenticateStudent(StudentDTO s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
