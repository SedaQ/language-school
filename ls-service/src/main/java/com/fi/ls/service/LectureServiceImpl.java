package com.fi.ls.service;

import com.fi.ls.dao.LectureDao;
import com.fi.ls.entity.Lecture;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Marek Nedbal (357293)
 */
public class LectureServiceImpl implements LectureService {
    
    private LectureDao lectureDao;
    
    @Inject
    public LectureServiceImpl(LectureDao lectureDao){
    	this.lectureDao = lectureDao;
    }
    
    @Override
    public void create(Lecture lecture) {
        
        lectureDao.create(lecture);
        
    }

    @Override
    public Lecture findById(Long id) {
        
        return lectureDao.findById(id);
        
    }

    @Override
    public Lecture update(Lecture lecture) {
        
        return lectureDao.update(lecture);
        
    }

    @Override
    public void remove(Lecture lecture) {
        
        lectureDao.remove(lecture);
        
    }

    @Override
    public List<Lecture> findAll() {
        
        return lectureDao.findAll();
        
    }
    
}
