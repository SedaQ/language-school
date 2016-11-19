package com.fi.ls.service;

import com.fi.ls.dao.LanguageDao;
import com.fi.ls.entity.Language;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.enums.ProficiencyLevel;
import com.fi.ls.exceptions.ServiceLayerException;
import javax.persistence.PersistenceException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 *
 * @author Lukáš Daubner (410034)
 */
public class LanguageServiceTest {
    
    @Mock
    private LanguageDao languageDao;
    
    private LanguageService languageService;
    
    private Language lan1;
    private Language lan2;
    private Lecturer l; 
    
    @BeforeClass
    public void beforeClass() {
            MockitoAnnotations.initMocks(this);
            languageService = new LanguageServiceImpl(languageDao);
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
        Language lan3 = new Language();
        lan3.setLanguage("LOGO");
        lan3.setProficiencyLevel(ProficiencyLevel.C1);
        l.addLanguage(lan3);
        
        languageService.create(lan3);
        
        verify(languageDao, times(1)).create(lan3);
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testCreateThrows() {
        doThrow(new PersistenceException("")).when(languageDao).create(any(Language.class));
        Language lan3 = new Language();
        lan3.setLanguage("LOGO");
        lan3.setProficiencyLevel(ProficiencyLevel.C1);
        l.addLanguage(lan3);
        
        languageService.create(lan3);
        
        fail("Expected ServiceException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCreateNull() {
        
        languageService.create(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testFindAll() throws ServiceLayerException {
        
        languageService.findAll();
        
        verify(languageDao, times(1)).findAll();
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testFindAllThrows() {
        doThrow(new PersistenceException("")).when(languageDao).findAll();
        
        languageService.findAll();
        
        fail("Expected ServiceException");
    }
    
    @Test
    public void testfindById() {
        
        languageService.findById(Long.MAX_VALUE);
        
        verify(languageDao, times(1)).findById(Long.MAX_VALUE);
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testfindByIdThrows() throws ServiceLayerException {
        doThrow(new PersistenceException("")).when(languageDao).findById(any(Long.class));
        
        languageService.findById(1L);
        
        fail("Expected ServiceException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testfindByIdNull() {
        
        languageService.findById(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testRemove() {
        
        languageService.remove(lan1);
        
        verify(languageDao, times(1)).remove(lan1);
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testRemoveThrows() {
        doThrow(new PersistenceException("")).when(languageDao).remove(any(Language.class));
        
        languageService.remove(lan1);
        
        fail("Expected ServiceException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testRemoveNull() {
        
        languageService.remove(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testUpdate() {
        lan1.setLanguage("LOGO");

        languageService.update(lan1);

        verify(languageDao, times(1)).update(lan1);
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testUpdateThrows() {
        doThrow(new PersistenceException("")).when(languageDao).update(any(Language.class));
        lan1.setLanguage("LOGO");
        
        languageService.update(lan1);
        
        fail("Expected ServiceException");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testUpdateNull() {
        
        languageService.update(null);
        
        fail("Expected IllegalArgumentException");
    }
}
