package com.fi.ls.service;

import com.fi.ls.dao.LSUserRepository;
import com.fi.ls.dao.StudentDao;
import com.fi.ls.entity.LSUser;
import com.fi.ls.entity.Student;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Matúš
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Inject
    private StudentDao studentDao;
    
    @Autowired
    private LSUserRepository userDao;

    @Override
    public void create(Student s, String unecryptedPassword) {
        s.setPasswordHash(UserPasswordEncryption.createHash(unecryptedPassword));
        this.studentDao.create(s);
    }

    @Override
    public Student findByBirthNumber(String birthNumber) {
        for (Student s : this.studentDao.findAll()){
            if (s.getBirthNumber().equals(birthNumber)){
                return s;
            }
        }
        return null;
    }

    @Override
    public Student findByEmail(String email) {
       LSUser u = userDao.findByEmail(email);
       if (u == null) return null;
       if (u instanceof Student){
           return (Student)u;
       } else {
           return null;
       }
    }

    @Override
    public Student findById(Long id) {
        return this.studentDao.findById(id);
    }

    @Override
    public List<Student> findAllStudents() {
        return this.studentDao.findAll();
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        List<Student> ls = new ArrayList<>();
        
        for(Student s : this.studentDao.findAll()){
            if (s.getFirstName().equals(firstName)){
                ls.add(s);
            }
        }
            
        return ls;
    }

    @Override
    public List<Student> findBySurname(String surname) {
        List<Student> ls = new ArrayList<>();
        
        for(Student s : this.studentDao.findAll()){
            if (s.getSurname().equals(surname)){
                ls.add(s);
            }
        }
            
        return ls;
    }

    @Override
    public Student update(Student s) {
        return this.studentDao.update(s);
    }

    @Override
    public void remove(Student s) {
        this.studentDao.remove(s);
    }

    @Override
    public boolean authenticateStudent(Student s, String password) {
        return UserPasswordEncryption.validatePassword(password, s.getPasswordHash());
    }
    
}
