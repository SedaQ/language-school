package com.fi.ls.service;

import com.fi.ls.dao.LanguageDao;
import com.fi.ls.dao.LecturerDao;
import com.fi.ls.entity.Language;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.enums.ProficiencyLevel;
import com.fi.ls.exceptions.ServiceLayerException;
import javax.persistence.PersistenceException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;
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
}
