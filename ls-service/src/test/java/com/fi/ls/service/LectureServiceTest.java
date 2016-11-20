package com.fi.ls.service;

import com.fi.ls.dao.LectureDao;
import com.fi.ls.entity.Lecture;
import com.fi.ls.exceptions.ServiceLayerException;
import java.time.LocalDateTime;
import java.time.Month;
import javax.persistence.PersistenceException;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import static org.testng.Assert.fail;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Marek Nedbal (357293)
 */
public class LectureServiceTest {
    
    @Mock
    private LectureDao lectureDao;

    private LectureService lectureService;
    
    Lecture lecture1;
    Lecture lecture2;
    Lecture lecture3;
    
    @BeforeClass
    public void beforeClass() {
        
    	MockitoAnnotations.initMocks(this);
        lectureService = new LectureServiceImpl(lectureDao);
        
    }
    
    @BeforeMethod
    public void beforeMethod() {
        
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
        
    }
 
    @Test
    public void testCreate() throws ServiceLayerException {
        
        lectureService.create(lecture1);   
        verify(lectureDao,times(1)).create(lecture1);
        
    }

    @Test(expectedExceptions = ServiceLayerException.class)
    public void testCreateWithException() throws ServiceLayerException {
        
       doThrow(new PersistenceException()).doNothing().when(lectureDao).create(any(Lecture.class)); 
       lectureService.create(lecture1);
       fail("ServiceLayerException expected!");
       
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateWithNullArgument() throws ServiceLayerException {
        
        lectureService.create(null);
        fail("IllegalArgumentException expected!");
        
    }

    @Test
    public void testFindAll() throws ServiceLayerException {
        
        lectureService.findAll();
        verify(lectureDao, times(1)).findAll();
        
    }

    @Test(expectedExceptions = ServiceLayerException.class)
    public void testFindAllWithException() throws ServiceLayerException {
        
        doThrow(new PersistenceException()).when(lectureDao).findAll();
        lectureService.findAll();
        fail("ServiceLayerException expected!");
        
    }

    @Test
    public void testFindById() throws ServiceLayerException {
        
        lectureService.findById(Long.MIN_VALUE);
        verify(lectureDao, times(1)).findById(Long.MIN_VALUE);
        
    }

    @Test(expectedExceptions = ServiceLayerException.class)
    public void testFindByIdWithException() throws ServiceLayerException {
        
        doThrow(new PersistenceException()).when(lectureDao).findById(any(Long.class));
        lectureService.findById(Long.MAX_VALUE);
        fail("ServiceLayerException expected!");
        
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByIdWithNullArgument() throws ServiceLayerException {
        
        lectureService.findById(null);
        fail("IllegalArgumentException expected!");
        
    }

    @Test
    public void testRemove() throws ServiceLayerException {
        
        lectureService.remove(lecture1);
        verify(lectureDao, times(1)).remove(lecture1);
        
    }

    @Test(expectedExceptions = ServiceLayerException.class)
    public void testRemoveWithException() throws ServiceLayerException {
        
        doThrow(new PersistenceException()).when(lectureDao).remove(any(Lecture.class));
        lectureService.remove(lecture1);
        fail("ServiceLayerException expected!");
        
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemoveWithNullArgument() throws ServiceLayerException {
        
        lectureService.remove(null);
        fail("IllegalArgumentException expected!");
        
    }

    @Test
    public void testUpdate() throws ServiceLayerException {
        
        lectureService.create(lecture3);
        lecture3.setTopic("Something boring..");
        lectureService.update(lecture3);
        verify(lectureDao, times(1)).update(lecture3);
        
    }

    @Test(expectedExceptions = ServiceLayerException.class)
    public void testUpdateWithException() throws ServiceLayerException {
        
        doThrow(new PersistenceException()).when(lectureDao).update(any(Lecture.class));
        lectureService.create(lecture1);
        lecture1.setTopic("Something boring..");
        lectureService.update(lecture1);
        fail("ServiceLayerException expected!");
        
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateWithNullArgument() throws ServiceLayerException {
        
        lectureService.update(null);
        fail("IllegalArgumentExcetion expected!");
        
    }
    
}
