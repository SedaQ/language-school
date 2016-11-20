package com.fi.ls.facade;

import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.lecturer.LecturerCreateDTO;
import com.fi.ls.dto.lecturer.LecturerDTO;
import com.fi.ls.entity.Language;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.exceptions.ServiceLayerException;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.LecturerService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 *
 * @author Lukas Daubner (410034)
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LecturerFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    LecturerService lecturerService;
    
    @Mock
    BeanMapping beanMapping;
    
    LecturerFacade lecturerFacade;
    
    LecturerDTO lecturer;
    LecturerCreateDTO lecturerCreate;
    LectureDTO lecture;
    
    @BeforeClass
    public void beforeClass() {
        MockitoAnnotations.initMocks(this);
        doReturn(Optional.of(new Lecturer())).when(beanMapping).mapTo(any(LecturerDTO.class), eq(Lecturer.class));
        doReturn(Optional.of(new Lecturer())).when(beanMapping).mapTo(any(LecturerCreateDTO.class), eq(Lecturer.class));
        doReturn(Optional.of(new LecturerDTO())).when(beanMapping).mapTo(any(Lecturer.class), eq(LecturerDTO.class));
        doReturn(Optional.of(new Lecture())).when(beanMapping).mapTo(any(LectureDTO.class), eq(Lecture.class));
        doReturn(Optional.of(new LanguageDTO())).when(beanMapping).mapTo(any(Language.class), eq(LanguageDTO.class));
        
        lecturerFacade = new LecturerFacadeImpl(lecturerService, beanMapping);
    }
    
    @BeforeMethod
    public void beforeMethod() {
        lecturer = new LecturerDTO();
        lecturerCreate = new LecturerCreateDTO();
        lecture = new LectureDTO();
    }
    
    @AfterMethod
    public void afterMethod() {
        reset(lecturerService);
    }
    
    @Test
    public void testCreateLecturer() {
        
        lecturerFacade.createLecturer(lecturerCreate);
        
        verify(lecturerService, times(1)).create(any(Lecturer.class));
    }
    
    @Test
    public void testCreateLecturerThrows() {
        doThrow(new ServiceLayerException("")).when(lecturerService).create(any(Lecturer.class));
        
        Optional<LecturerDTO> output = lecturerFacade.createLecturer(lecturerCreate);
        
        assertEquals(output, Optional.empty());
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCreateLecturerNull() {
        
        lecturerFacade.createLecturer(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testGetLecturerById() {
        
        lecturerFacade.getLecturerById(Long.MAX_VALUE);
        
        verify(lecturerService, times(1)).findById(any(Long.class));
    }
    
    @Test
    public void testGetLecturerByIdThrows() {
        doThrow(new ServiceLayerException("")).when(lecturerService).findById(any(Long.class));
        
        Optional<LecturerDTO> output = lecturerFacade.getLecturerById(Long.MAX_VALUE);
        
        assertEquals(output, Optional.empty());
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testGetLecturerByIdNull() {
        
        lecturerFacade.getLecturerById(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testUpdateLecturer() {
        
        lecturerFacade.updateLecturer(lecturer);
        
        verify(lecturerService, times(1)).update(any(Lecturer.class));
    }
    
    @Test
    public void testUpdateLecturerThrows() {
        doThrow(new ServiceLayerException("")).when(lecturerService).update(any(Lecturer.class));
        
        Optional<LecturerDTO> output = lecturerFacade.updateLecturer(lecturer);
        
        assertEquals(output, Optional.empty());
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testUpdateLecturerNull() {
        
        lecturerFacade.updateLecturer(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testDeleteLecturer() {
        
        lecturerFacade.deleteLecturer(lecturer);
        
        verify(lecturerService, times(1)).remove(any(Lecturer.class));
    }
    
    @Test
    public void testDeleteLecturerThrows() {
        doThrow(new ServiceLayerException("")).when(lecturerService).remove(any(Lecturer.class));
        
        Boolean output = lecturerFacade.deleteLecturer(lecturer);
        
        assertFalse(output);
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testDeleteLecturerNull() {
        
        lecturerFacade.deleteLecturer(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testGetAllLecturers() {
        
        lecturerFacade.getAllLecturers();
        
        verify(lecturerService, times(1)).findAll();
    }
    
    @Test
    public void testGetAllLecturersThrows() {
        doThrow(new ServiceLayerException("")).when(lecturerService).findAll();
        
        List<LecturerDTO> output = lecturerFacade.getAllLecturers();
        
        assertEquals(output, Collections.EMPTY_LIST);
    }
    
    @Test
    public void testDeleteLecture() {
        
        lecturerFacade.deleteLecture(lecturer, lecture);
        
        verify(lecturerService, times(1)).deleteLecture(any(Lecturer.class), any(Lecture.class));
    }
    
    @Test
    public void testDeleteLectureThrows() {
        doThrow(new ServiceLayerException("")).when(lecturerService).deleteLecture(any(Lecturer.class), any(Lecture.class));
        
        Boolean output = lecturerFacade.deleteLecture(lecturer, lecture);
        
        assertFalse(output);
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testDeleteLectureLecturerNull() {
        
        lecturerFacade.deleteLecture(null, lecture);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testDeleteLectureLectureNull() {
        
        lecturerFacade.deleteLecture(lecturer, null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testDeleteLectures() {
        
        lecturerFacade.deleteLectures(lecturer, Arrays.asList(lecture));
        
        verify(lecturerService, times(1)).deleteLectures(any(Lecturer.class), any(List.class));
    }
    
    @Test
    public void testDeleteLecturesThrows() {
        doThrow(new ServiceLayerException("")).when(lecturerService).deleteLectures(any(Lecturer.class), any(List.class));
        
        Boolean output = lecturerFacade.deleteLectures(lecturer, Arrays.asList(lecture));
        
        assertFalse(output);
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testDeleteLecturesLecturerNull() {
        
        lecturerFacade.deleteLectures(null, Arrays.asList(lecture));
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testDeleteLecturesLecturesNull() {
        
        lecturerFacade.deleteLectures(lecturer, null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testFindAllLecturerLanguages() {
        
        lecturerFacade.findAllLecturerLanguages(lecturer);
        
        verify(lecturerService, times(1)).findAllLecturerLanguages(any(Lecturer.class));
    }
    
    @Test
    public void testFindAllLecturerLanguagesThrows() {
        doThrow(new ServiceLayerException("")).when(lecturerService).findAllLecturerLanguages(any(Lecturer.class));
        
        List<LanguageDTO> output = lecturerFacade.findAllLecturerLanguages(lecturer);
        
        assertEquals(output, Collections.EMPTY_LIST);
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindAllLecturerNull() {
        
        lecturerFacade.findAllLecturerLanguages(null);
        
        fail("Expected IllegalArgumentException");
    }
}
