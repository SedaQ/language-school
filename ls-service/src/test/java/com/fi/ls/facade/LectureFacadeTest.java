package com.fi.ls.facade;

import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.dto.lecture.LectureCreateDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.entity.Lecture;
import com.fi.ls.exceptions.ServiceLayerException;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.LectureService;
import java.util.Collections;
import java.util.Optional;
import javax.transaction.Transactional;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Marek Nedbal (357293)
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LectureFacadeTest extends AbstractTestNGSpringContextTests {
   
    @Mock
    LectureService lectureService;
    
    @Mock
    BeanMapping beanMapping;
    
    LectureFacade lectureFacade;
    
    LectureDTO lecture;
    
    LectureCreateDTO lectureCreate;
    
    @BeforeClass
    public void beforeClass() {
        
        MockitoAnnotations.initMocks(this);
        doReturn(Optional.of(new Lecture())).when(beanMapping).mapTo(any(LectureDTO.class), eq(Lecture.class));
        doReturn(Optional.of(new Lecture())).when(beanMapping).mapTo(any(LectureCreateDTO.class), eq(Lecture.class));
        doReturn(Optional.of(new LectureDTO())).when(beanMapping).mapTo(any(Lecture.class), eq(LectureDTO.class));        
        lectureFacade = new LectureFacadeImpl(lectureService, beanMapping);
        
    }
    
    @BeforeMethod
    public void beforeMethod() {
        
        lecture = new LectureDTO();
        lectureCreate = new LectureCreateDTO();
        
    }
    
    @AfterMethod
    public void afterMethod() {
        
        reset(lectureService);
        
    }

    @Test
    public void testCreate() {
        
        lectureFacade.createLecture(lectureCreate);
        verify(lectureService, times(1)).create(any(Lecture.class));
        
    }
    /*
    @Test
    public void testCreateWithException() {
        
        doThrow(new ServiceLayerException("")).when(lectureService).create(any(Lecture.class));
        
        Optional<LectureDTO> output = lectureFacade.createLecture(lectureCreate);
        assertEquals(output, Optional.empty());
        
        //assertEquals(lectureFacade.createLecture(lectureCreate), Optional.empty());
        
    }
    */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateWithNullArgument() {
        
        lectureFacade.createLecture(null);
        fail("IllegalArgumentException expected!");
        
    }
    
    @Test
    public void testFindById() {
        
        lectureFacade.getLectureById(Long.MIN_VALUE);
        verify(lectureService, times(1)).findById(Long.MIN_VALUE);
        
    }

    @Test
    public void testFindByIdWithException() {
        
        doThrow(new ServiceLayerException("")).when(lectureService).findById(any(Long.class));
        assertEquals(lectureFacade.getLectureById(Long.MIN_VALUE), Optional.empty());
        
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByIdWithNullArgument() {
        
        lectureFacade.getLectureById(null);
        fail("IllegalArgumentException expected!");
        
    }
    
    @Test
    public void testUpdate() {
        
        lectureFacade.updateLecture(Long.MIN_VALUE);
        verify(lectureService, times(1)).update(any(Lecture.class));
        
    }
    /*
    @Test
    public void testUpdateWithException() {
        
        doThrow(new ServiceLayerException("")).when(lectureService).update(any(Lecture.class));
        assertEquals(lectureFacade.updateLecture(Long.MIN_VALUE), Optional.empty());
        
    }
    */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateWithNullArgument() {
        
        lectureFacade.updateLecture(null);
        fail("IllegalArgumentException expected!");
        
    }
    
    @Test
    public void testDelete() {
        
        lectureFacade.deleteLecture(Long.MIN_VALUE);
        verify(lectureService, times(1)).remove(any(Lecture.class));
        
    }
    
    @Test
    public void testDeleteWithException() {
        
        doThrow(new ServiceLayerException("")).when(lectureService).remove(any(Lecture.class));
        assertFalse(lectureFacade.deleteLecture(Long.MIN_VALUE));
        
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteWithNullArgument() {
        
        lectureFacade.deleteLecture(null);
        fail("IllegalArgumentException expected!");
        
    }
    
    @Test
    public void testFindAll() {
        
        lectureFacade.getAllLectures();
        verify(lectureService, times(1)).findAll();
        
    }
    
    @Test
    public void testFindAllWithException() {
        
        doThrow(new ServiceLayerException("")).when(lectureService).findAll();
        assertEquals(lectureFacade.getAllLectures(), Collections.EMPTY_LIST);
        
    }
  
}
