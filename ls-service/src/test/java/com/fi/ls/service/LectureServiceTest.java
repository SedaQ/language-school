package com.fi.ls.service;

import com.fi.ls.dao.LectureDao;
import com.fi.ls.entity.Lecture;
import java.time.LocalDateTime;
import java.time.Month;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author Marek Nedbal (357293)
 */
public class LectureServiceTest {

    @Autowired
    private LectureDao lectureDao;

    @Autowired
    @InjectMocks
    private LectureService lectureService;
    
    Lecture lecture1;
    Lecture lecture2;
    Lecture lecture3;
    
    @BeforeClass
    public void setup() throws ServiceException {
    	MockitoAnnotations.initMocks(this);
    }
    
    @BeforeClass
    public void beforeClass() {
        
        lecture1 = new Lecture();
        lecture2 = new Lecture();
        lecture3 = new Lecture();
        lecture1.setDayTime(LocalDateTime.of(2016, Month.NOVEMBER, 29, 16, 0));
        lecture2.setDayTime(LocalDateTime.of(2016, Month.NOVEMBER, 29, 16, 0));
        lecture3.setDayTime(LocalDateTime.of(2016, Month.NOVEMBER, 30, 12, 0));
        lecture1.setClassroomId("B403");
        lecture2.setClassroomId("B403");
        lecture3.setClassroomId("B403");
        lecture1.setTopic("English");
        lecture2.setTopic("English");
        lecture3.setTopic("Farsi");
        
        lectureService.create(lecture1);
        lectureService.create(lecture2);
        lectureService.create(lecture3);
        
    } 
    
}
