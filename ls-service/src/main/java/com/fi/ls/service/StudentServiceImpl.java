package com.fi.ls.service;

import com.fi.ls.dao.StudentDao;
import com.fi.ls.entity.Student;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Matúš
 */
public class StudentServiceImpl implements StudentService {

    @Inject
    private StudentDao studentDao;
    
    @Override
    public void create(Student s) {
        this.studentDao.create(s);
    }

    @Override
    public Student findById(Long id) {
        return this.studentDao.findById(id);
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
    public List<Student> findAll() {
        return this.studentDao.findAll();
    }
    
}
