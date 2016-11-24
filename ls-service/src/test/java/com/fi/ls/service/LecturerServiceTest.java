package com.fi.ls.service;

import com.fi.ls.dao.LanguageDao;
import com.fi.ls.dao.LecturerDao;
import com.fi.ls.entity.Language;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.enums.ProficiencyLevel;
import com.fi.ls.exceptions.ServiceLayerException;
import java.util.Arrays;
import javax.persistence.PersistenceException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Lukas Daubner (410034)
 */
public class LecturerServiceTest {
    @Mock
    private LecturerDao lecturerDao;
    
    @Mock
    private LanguageDao languageDao;

    private LecturerService lecturerService;
    
    private Language lan1;
    private Language lan2;
    private Lecturer l; 
    private Lecture lec;
    
    @BeforeClass
    public void beforeClass() {
            MockitoAnnotations.initMocks(this);
            lecturerService = new LecturerServiceImpl(lecturerDao, languageDao);
    }
    
    @BeforeMethod
    public void beforeMethod() {
            l = new Lecturer();
            l.setFirstName("Horus");
            l.setSurname("Lupercal");
            l.setNickname("Arch-Traitor");
            l.setEmail("Horus@lunawolves.terra");
            l.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");
            
            lan1 = new Language();
            lan1.setLanguage("HIGH");
            lan1.setProficiencyLevel(ProficiencyLevel.A2);
            
            lan2 = new Language();
            lan2.setLanguage("CTHO");
            lan2.setProficiencyLevel(ProficiencyLevel.A1);
            
            l.addLanguage(lan1);
            l.addLanguage(lan2);
            
            lec = new Lecture();
            lec.addLecturer(l);
    }
    
    @AfterMethod
    public void afterMethod() {
        reset(lecturerDao, languageDao);
    }
    
    @Test
    public void testCreate() {
        Lecturer l2 = new Lecturer();
        l2.setNickname("Little Horus");
        l2.setFirstName("Horus");
        l2.setSurname("Aximand");
        l2.setEmail("Horus.Little@lunawolves.terra");
        l2.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");
        Language lan3 = new Language();
        lan3.setLanguage("CTHO");
        lan3.setProficiencyLevel(ProficiencyLevel.A1);
        l2.addLanguage(lan3);
        
        lecturerService.create(l2);
        
        verify(lecturerDao, times(1)).create(l2);
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testCreateThrows() {
        doThrow(new PersistenceException("")).when(lecturerDao).create(any(Lecturer.class));
        Lecturer l2 = new Lecturer();
        l2.setNickname("Little Horus");
        l2.setFirstName("Horus");
        l2.setSurname("Aximand");
        l2.setEmail("Horus.Little@lunawolves.terra");
        l2.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");
        Language lan3 = new Language();
        lan3.setLanguage("CTHO");
        lan3.setProficiencyLevel(ProficiencyLevel.A1);
        l2.addLanguage(lan3);
        
        lecturerService.create(l2);
        
        fail("Expected ServiceException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCreateNull() {
        
        lecturerService.create(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testFindAll() throws ServiceLayerException {
        
        lecturerService.findAll();
        
        verify(lecturerDao, times(1)).findAll();
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testFindAllThrows() {
        doThrow(new PersistenceException("")).when(lecturerDao).findAll();
        
        lecturerService.findAll();
        
        fail("Expected ServiceException");
    }
    
    @Test
    public void testfindById() {
        
        lecturerService.findById(Long.MAX_VALUE);
        
        verify(lecturerDao, times(1)).findById(Long.MAX_VALUE);
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testfindByIdThrows() throws ServiceLayerException {
        doThrow(new PersistenceException("")).when(lecturerDao).findById(any(Long.class));
        
        lecturerService.findById(1L);
        
        fail("Expected ServiceException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testfindByIdNull() {
        
        lecturerService.findById(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testRemove() {
        
        lecturerService.remove(l);
        
        verify(lecturerDao, times(1)).remove(l);
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testRemoveThrows() {
        doThrow(new PersistenceException("")).when(lecturerDao).remove(any(Lecturer.class));
        
        lecturerService.remove(l);
        
        fail("Expected ServiceException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testRemoveNull() {
        
        lecturerService.remove(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testUpdate() {
        l.setNickname("BigBadEvilGuy");

        lecturerService.update(l);

        verify(lecturerDao, times(1)).update(l);
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testUpdateThrows() {
        doThrow(new PersistenceException("")).when(lecturerDao).update(any(Lecturer.class));
        l.setNickname("BigBadEvilGuy");
        
        lecturerService.update(l);
        
        fail("Expected ServiceException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testUpdateNull() {
        
        lecturerService.update(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testDeleteLectureLecturerNull() {
        
        lecturerService.deleteLecture(null, lec);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testDeleteLectureLectureNull() {
        
        lecturerService.deleteLecture(l, null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testDeleteLecture() {

        lecturerService.deleteLecture(l, lec);

        verify(lecturerDao, times(1)).update(l);
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testDeleteLectureThrows() {
        doThrow(new PersistenceException("")).when(lecturerDao).update(any(Lecturer.class));
        
        lecturerService.deleteLecture(l, lec);
        
        fail("Expected ServiceException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testDeleteLecturesLecturerNull() {
        
        lecturerService.deleteLectures(null, Arrays.asList(lec));
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testDeleteLecturesLecturesNull() {
        
        lecturerService.deleteLecture(l, null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testDeleteLectures() {

        lecturerService.deleteLectures(l, Arrays.asList(lec));

        verify(lecturerDao, times(1)).update(l);
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testDeleteLecturesThrows() {
        doThrow(new PersistenceException("")).when(lecturerDao).update(any(Lecturer.class));
        
        lecturerService.deleteLectures(l, Arrays.asList(lec));
        
        fail("Expected ServiceException");
    }
    
    @Test
    public void testFindAllLecturerLanguages() {

        lecturerService.findAllLecturerLanguages(l);

        verify(lecturerDao, times(1)).findAllLecturerLanguages(l);
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testFindAllLecturerLanguagesThrows() {
        doThrow(new PersistenceException("")).when(lecturerDao).findAllLecturerLanguages(any(Lecturer.class));
        
        lecturerService.findAllLecturerLanguages(l);
        
        fail("Expected ServiceException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindAllLecturerLanguagesNull() {
        
        lecturerService.findAllLecturerLanguages(null);
        
        fail("Expected IllegalArgumentException");
    }
}
